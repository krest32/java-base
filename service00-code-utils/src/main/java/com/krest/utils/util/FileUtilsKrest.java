package com.krest.utils.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Data
public class FileUtilsKrest {


    // 默认参数
    private int coreThreadSize = 10;
    private int maxThreadSize = 20;
    private int queueSize = 50000;
    private int keepAlive = 30;

    // 线程池
    ThreadPoolExecutor fileExecutor = null;

    static int streamDelKeyWords = 0;

    public FileUtilsKrest() {
    }

    /**
     * 构建线程池参数
     */
    public FileUtilsKrest(int coreSize, int maxThreadSize) {
        this.coreThreadSize = coreSize;
        this.maxThreadSize = maxThreadSize;
    }

    /**
     * @param coreSize      核心线程参数
     * @param maxThreadSize 最大线程参数
     * @param queueSize     队列参数
     */
    public FileUtilsKrest(int coreSize, int maxThreadSize, int queueSize) {
        this.coreThreadSize = coreSize;
        this.maxThreadSize = maxThreadSize;
        this.queueSize = queueSize;
    }

    /**
     * 构建线程池
     *
     * @return 线程池
     */
    private ThreadPoolExecutor createExecutor() {
        this.fileExecutor = new ThreadPoolExecutor(this.coreThreadSize,
                this.maxThreadSize,
                this.keepAlive,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue(this.queueSize),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return this.fileExecutor;
    }

    /**
     * 向某个文件中写入字符串，可选择追加或覆盖
     *
     * @param filePath 文件路径
     * @param context  输入的字符
     * @param isAppend 是否在文件中进行追加
     */
    public void writeStr(String filePath, String context, boolean isAppend) {
        BufferedWriter output = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                createFile(file);
            }
            output = new BufferedWriter(new FileWriter(file, isAppend));
            output.write(context);
            //换行
            output.write("\r\n");
            output.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public void createFile(File file) {
        if (!file.exists()) {
            mkDir(file.getParent());
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 递归删除空文件夹
     *
     * @param folderDir 目标文件夹String
     * @param isDelSelf 是否删除文件夹自身
     * @return 被删除的空文件夹的数量
     */
    public Integer clearEmptyFolder(String folderDir, Boolean isDelSelf) {
        return this.clearEmptyFolder(new File(folderDir), isDelSelf);
    }

    /**
     * 递归删除空文件夹
     *
     * @param delEmptyFolder 目标文件夹
     * @param isDelSelf      是否删除文件夹自身
     * @return 被删除的空文件夹的数量
     */
    public int clearEmptyFolder(File delEmptyFolder, Boolean isDelSelf) {
        int emptyFolder = 0;
        if (delEmptyFolder.exists() && delEmptyFolder.isDirectory()) {
            File[] dirs = delEmptyFolder.listFiles();
            // 如果依旧有子文件夹，那么就继续递归
            for (int i = 0; i < dirs.length; i++) {
                if (dirs[i].isDirectory()) {
                    emptyFolder += clearEmptyFolder(dirs[i], true);
                }
            }
            // 如果没有子文件，同时本身是文件夹，那么执行删除操作
            if (isDelSelf && delEmptyFolder.delete()) {
                emptyFolder++;
            }
        }
        return emptyFolder;
    }


    /***
     *  利用 Stream 流处理文件删除
     * @param folderDir
     * @param isDelSelf
     * @return
     */
    public int streamClearEmptyFolder(String folderDir, Boolean isDelSelf) {
        return this.streamClearEmptyFolder(new File(folderDir), isDelSelf);
    }


    /**
     * 利用 Stream 流处理文件删除
     *
     * @param targetFolder 目标文件夹
     * @param isDelSelf    是否删除文件夹自身
     * @return 返回删除的空文件夹的数量
     */
    public int streamClearEmptyFolder(File targetFolder, Boolean isDelSelf) {
        int streamEmptyFolder = 0;
        List<Integer> collect = null;
        if (targetFolder.exists() && targetFolder.isDirectory()) {
            File[] dirs = targetFolder.listFiles();
            List<File> fileList = Arrays.asList(dirs);
            collect = fileList.parallelStream().map(
                    file -> {
                        if (file.exists() && file.isDirectory()) {
                            return streamClearEmptyFolder(file, true);
                        } else {
                            return 0;
                        }
                    })
                    .collect(Collectors.toList());
            // java中如果文件夾非空，无法删除
            if (isDelSelf && targetFolder.delete()) {
                streamEmptyFolder++;
            }
        }
        for (int num : collect) {
            streamEmptyFolder += num;
        }
        return streamEmptyFolder;
    }


    /**
     * 复制文件到指定的位置
     *
     * @param oldFile 源文件
     * @param newFile 目标文件
     * @return boolean
     */
    public boolean copyFile(String oldFile, String newFile) {
        return this.copyFile(new File(oldFile), new File(newFile));
    }

    /**
     * 复制文件到指定的位置
     *
     * @param oldFile 源文件
     * @param newFile 目标文件
     * @return boolean
     */
    public boolean copyFile(File oldFile, File newFile) {
        // 要拷贝的文件
        if (!oldFile.exists()) {
            return false;
        }
        File outDir = new File(newFile.getParent());
        if (!outDir.exists() || !outDir.isDirectory()) {
            boolean dirFlag = mkDir(outDir.getPath());
            if (!dirFlag) {
                return false;
            }
        }
        try (FileInputStream fis = new FileInputStream(oldFile);
             FileOutputStream fos = new FileOutputStream(newFile);) {
            int c;
            // 创建字节数组
            byte[] b = new byte[1024 * 5];
            // 写入文件
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
                fos.flush();
            }
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 创建文件夹，如果文件夹已经存在，那么返回 true，如果创建失败，返回 false ;
     *
     * @param dirPath 目标路径
     * @return
     */
    public boolean mkDir(String dirPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (dirFile.exists() && dirFile.isDirectory()) {
            return true;
        } else if (!dirFile.exists() && !dirFile.isDirectory()) {
            return dirFile.mkdirs();
        } else {
            return false;
        }
    }


    /**
     * 删除文件文件夹目录(包含子文件夹)下，包含某个关键字的文件 适用于后缀名
     *
     * @param path     目标文件夹路径
     * @param keyWords 关键字集合
     * @return
     */
    int delFileCount = 0;

    public Integer delFileWithKeyWords(String path, List<String> keyWords,
                                       boolean isDelFolder, boolean isDelSelf) {
        if (path == null && path.length() == 0) {
            return delFileCount;
        }
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            return delFileCount;
        }
        if (dirFile.isFile() && check(keyWords, dirFile.getName())) {
            return delFileCount + 1;
        }
        if (dirFile.isDirectory()) {
            File[] files = dirFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                // 　如果是文件的情况
                if (files[i].isFile()) {
                    if (check(keyWords, fileName)) {
                        delFileCount += deleteFile(files[i]);
                    }
                    // 如果是文件夹的情况
                } else {
                    if (isDelFolder && check(keyWords, fileName)) {
                        delFileCount += deleteDirectory(files[i]);
                    } else {
                        delFileWithKeyWords(files[i].getAbsolutePath(), keyWords, isDelFolder, true);
                    }
                }
            }
            if (isDelSelf && dirFile.listFiles().length == 0) {
                delFileCount += deleteDirectory(dirFile);
            }
        }
        return delFileCount;
    }

    /**
     * @param path
     * @param keyWords
     * @return
     */

    AtomicInteger delCount = new AtomicInteger(0);

    public int executorDelFileWithKeyWords(String path, List<String> keyWords, boolean isDelFolder) {
        if (fileExecutor == null || fileExecutor.isShutdown()) {
            fileExecutor = createExecutor();
        }

        if (path == null && path.length() == 0) {
            return delCount.get();
        }
        File dirFile = new File(path);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists()) {
            return delCount.get();
        }
        // 如果是文件
        if (dirFile.isFile()) {
            if (check(keyWords, dirFile.getName()) && dirFile.delete()) {
                return delCount.getAndAdd(1);
            }
        } else {
            try {
                File[] files = dirFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    // 　如果是文件的情况
                    int finalI = i;
                    CompletableFuture<Integer> futureTask = CompletableFuture.supplyAsync(() -> {
                        if (files[finalI].isFile()) {
                            // 线程池判断是否包含需要删除的关键字
                            if (check(keyWords, fileName)) {
                                return deleteFile(files[finalI]);
                            }
                            return 0;
                        } else {
                            if (isDelFolder && check(keyWords, fileName)) {
                                delCount.getAndAdd(deleteDirectory(files[finalI]));
                            } else {
                                executorDelFileWithKeyWords(files[finalI].getAbsolutePath(), keyWords, isDelFolder);
                            }
                            return delFileCount;
                        }
                    }, fileExecutor);
                    delCount.getAndAdd(futureTask.get());
                }

            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } catch (ExecutionException e) {
                log.error(e.getLocalizedMessage(), e);
            } finally {
                // 判断队列是否为空
                while (!fileExecutor.getQueue().isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
        return delCount.get();
    }

    public boolean check(List<String> keyWords, String fileName) {
        return keyWords.stream().filter(keyWord ->
                fileName.contains(keyWord)).findFirst().isPresent();
    }

    /**
     * 获取文件的第n行的数据，从1开始计数
     *
     * @param filePath 源文件
     * @param row      获取文件的第几行
     * @return
     */
    public String getRowData(String filePath, Integer row) {
        File file = new File(filePath);
        String res = "";
        Integer linenumber = rowNum(filePath);
        if (row > linenumber) {
            return "";
        }
        if (file.isFile() && file.exists()) {
            try (FileReader fr = new FileReader(file);
                 LineNumberReader lnr = new LineNumberReader(fr);) {
                for (int i = 0; i < row; i++) {
                    res = lnr.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 读取文件一共有多少行
     *
     * @param filePath 源文件
     * @return
     */
    public Integer rowNum(String filePath) {
        File file = new File(filePath);
        Integer linenumber = 0;
        if (file.isFile() && file.exists()) {
            try (FileReader fr = new FileReader(file);
                 LineNumberReader lnr = new LineNumberReader(fr);) {
                String line = lnr.readLine();
                while (line != null && !line.equals("")) {
                    line = lnr.readLine();
                    linenumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return linenumber;
    }

    /**
     * 读取文件的内容，转换为字符串，可以是json、txt、xml等
     *
     * @param fileName 源文件路径
     * @return
     */
    public String loadFileAsString(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            throw new IllegalArgumentException("Operate File's Name Argument Exception.");
        } else {
            File operFile = new File(fileName);
            String line = "";
            StringBuilder fileResult = new StringBuilder();
            try (InputStreamReader isr =
                         new InputStreamReader(new FileInputStream(operFile), "UTF-8");
                 BufferedReader reader = new BufferedReader(isr);) {
                while ((line = reader.readLine()) != null) {
                    if (!line.equals("")) {
                        fileResult.append(line.trim());
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return fileResult.toString();
        }
    }

    /**
     * 保存字符串到文件当中
     *
     * @param content  需要保存的文档内容
     * @param filePath 目标文件，如果目标文件存在，那么将会被新文件覆盖
     * @return
     */
    public static boolean saveStringToFile(String content, String filePath) {
        if (content.equals("")) {
            return false;
        } else {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) {
                distFile.getParentFile().mkdirs();
            }
            boolean flag = true;
            try (BufferedReader bufferedReader = new BufferedReader(new StringReader(content));
                 BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(filePath))) {
                char[] buf = new char[1024];
                int len;
                while ((len = bufferedReader.read(buf)) != -1) {
                    bufferedWriter.write(buf, 0, len);
                }
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
                return flag;
            }
            return flag;
        }
    }

    /**
     * 通过复制的方式重新命名文件
     *
     * @param oldFile 源文件
     * @param newFile 新文件
     * @return boolean
     */
    public boolean renameFile(String oldFile, String newFile, boolean isDelSel) {
        File in = new File(oldFile);
        File out = new File(newFile);
        // 要拷贝的文件
        if (!in.exists()) {
            return false;
        }
        try (FileInputStream fis = new FileInputStream(in);
             FileOutputStream fos = new FileOutputStream(out);) {
            int c;
            // 创建字节数组
            byte[] b = new byte[1024 * 5];
            // 写入文件
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
            }
            fos.flush();
            if (isDelSel) {
                in.delete();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 入口方法 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param path 要删除的目录或文件路径
     * @return 删除成功返回 true，否则返回 false。
     */
    public int deleteFileOrDirectory(String path) {
        File dirFile = new File(path);
        int delCount = 0;
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists()) {
            return delCount;
        }
        // 如果是一个文件
        if (dirFile.isFile()) {
            return deleteFile(path);
        }
        // 如果是一个文件夹：删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                delCount += deleteFile(files[i]);
                // 删除子目录
            } else {
                delCount += deleteDirectory(files[i]);
            }
        }
        // 删除当前目录
        delCount += dirFile.delete() ? 1 : 0;
        return delCount;
    }


    /**
     * 删除单个文件
     *
     * @param path 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private int deleteFile(String path) {
        return this.deleteFile(new File(path));
    }

    /**
     * 删除文
     *
     * @param file
     * @return
     */
    public int deleteFile(File file) {
        try {
            // 如果文件不存在直接返回true
            if (!file.exists()) {
                return 0;
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                boolean delFlag = false;
                for (int i = 0; i < 10; i++) {
                    delFlag = file.delete();
                    if (delFlag) {
                        return 1;
                    } else {
                        // 如果删除不成功，那就就是用GC，然后进行强制删除，但是这种方法不推荐
                        System.gc();
                        Thread.sleep(1000);
                    }
                }
            }
            return 0;
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param path 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public int deleteDirectory(String path) {
        return deleteDirectory(new File(path));
    }


    public int deleteDirectory(File file) {
        int delCount = 0;
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!file.exists() || !file.isDirectory()) {
            return 0;
        }
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                delCount += deleteFile(files[i]);
            } else {
                // 删除子目录
                delCount += deleteDirectory(files[i]);
            }
        }
        delCount += file.delete() ? 1 : 0;
        // 删除当前目录
        return delCount;
    }

    /**
     * 统计文件数量，包含当前文件夹
     *
     * @param folder 目标文件夹
     * @return
     */
    public List<File> getAllFile(String folder) {
        File file = new File(folder);
        List<File> list = new ArrayList<>();
        if (file.exists()) {
            if (file.isFile()) {
                list.add(file);
            }
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (file.exists()) {
                        if (file.isFile()) {
                            list.add(files[i]);
                        }
                        if (file.isDirectory()) {
                            list.addAll(getAllFile(files[i].getAbsolutePath()));
                        }
                    }
                }
            }
        }
        return list;
    }
}
