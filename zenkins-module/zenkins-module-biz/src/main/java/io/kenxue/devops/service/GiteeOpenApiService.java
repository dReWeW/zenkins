package io.kenxue.devops.service;

import java.util.List;
import com.gitee.api.model.Branch;
import com.gitee.api.model.RepositoriesCommit;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
public interface GiteeOpenApiService {
    Branch getSingleBranch(String owner, String repoName, String branchName);

    RepositoriesCommit getSingleCommit(String owner, String repoName, String sha);

    List<Branch> getAllRepositoryBranches(String owner, String repoName);
}
