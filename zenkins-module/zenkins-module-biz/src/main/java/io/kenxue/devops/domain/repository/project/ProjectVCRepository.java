package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */

public interface ProjectVCRepository {
    void create(ProjectVersionCommitDict projectVersionCommitDict);
    void update(ProjectVersionCommitDict projectVersionCommitDict);
    ProjectVersionCommitDict getById(Long id);
    ProjectVersionCommitDict getByProjectIdAndCommit(Long projectId, String version);
    ProjectVersionCommitDict getByProjectIdAndVersion(Long projectId, String version);
}
