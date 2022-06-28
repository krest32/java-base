package com.krest.es.service;



import com.krest.Service.Response.R;
import com.krest.es.entity.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/23 17:53
 * @description:
 */
public interface EsService {

    R getUserById(String id) throws ExecutionException, InterruptedException;

    R savaToEs(User user) throws IOException;

    R deleteEsUser(String id) throws IOException;
}
