package io.kenxue.devops.application.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/11/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum JenkinsParams {
    PROJECT_NAME("project_name"),
    PROJECT_REPOSITORY_URL("project_repository_url"),
    BRANCH("branch"),
    SELECT_SSH_NAME("select_ssh_name"),
    SELECT_OPTION_TYPE("select_option_type"),
    BACK_VERSION("back_version"),
    PORT("port"),
    REPO_OWNER("repo_owner"),
    VERSION_TAG("version_tag"),
    COMMIT_TAG("commit_tag"),
    NGINX_CONF_FILE("nginx_conf_file"),
    NGINX_RECYCLE_CONF_FILE("nginx_recycle_conf_file"),
    NGINX_CONF_SCRIPT("nginx_conf_script"),
    CONTAINER_NAME("container_name"),
    ROLLBACK_OLD_TAG("rollback_old_tag"),
    ROLLBACK_NEW_TAG("rollback_new_tag"),
    ADD_SERVERS("add_servers"),
    DEL_SERVERS("del_servers"),
    PROD("prod"),
    STABLE("stable");
    private String name;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public enum JenkinsStage {
        BUILD("build"),
        DEPLOY("deploy"),
        ROLLBACK("rollback"),

        RECYCLE("recycle"),

        EXPANSION("expansion");
        private String name;
    }
}
