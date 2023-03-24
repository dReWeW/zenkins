package io.kenxue.devops.application.gitee.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.gitee.api.ApiClient;
import com.gitee.api.api.RepositoriesApi;
import com.gitee.api.model.Branch;
import com.gitee.api.model.RepositoriesCommit;
import com.google.common.collect.Lists;

import io.kenxue.devops.infrastructure.config.GiteeOpenApiConfig;
import io.kenxue.devops.service.GiteeOpenApiService;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Service
public class GiteeOpenApiServiceImpl implements GiteeOpenApiService, InitializingBean {
    private RepositoriesApi repositoriesApi;

    @Resource
    private GiteeOpenApiConfig giteeOpenApiConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        repositoriesApi = new ApiClient().createService(RepositoriesApi.class);
    }
    @Override
    public Branch getSingleBranch(String owner, String repoName, String branchName) {
        Observable<Branch> observable =
            repositoriesApi.getV5ReposOwnerRepoBranchesBranch(owner, repoName, branchName, giteeOpenApiConfig.getAccessToken());
        return observable.toBlocking().first();
    }
    @Override
    public RepositoriesCommit getSingleCommit(String owner, String repoName, String sha) {
        Observable<RepositoriesCommit> observable =
            repositoriesApi.getV5ReposOwnerRepoCommitsSha(owner, repoName, sha, giteeOpenApiConfig.getAccessToken());
        return observable.toBlocking().first();
    }

    @Override
    public List<Branch> getAllRepositoryBranches(String owner, String repoName) {
        List<Branch> rbranches = Lists.newArrayList();
        Observable<List<Branch>> observable =
            repositoriesApi.getV5ReposOwnerRepoBranches(owner, repoName, giteeOpenApiConfig.getAccessToken());
        observable.subscribe(new Observer<List<Branch>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Branch> branches) {
                rbranches.addAll(branches);
            }
        });
        return rbranches;
    }
}
