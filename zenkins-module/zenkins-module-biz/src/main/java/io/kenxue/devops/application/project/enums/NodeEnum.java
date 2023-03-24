package io.kenxue.devops.application.project.enums;

import com.offbytwo.jenkins.model.BuildResult;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
public enum NodeEnum {
    BUILD, DEV, QA, PROD;

    public enum StatusEnum {
        PROCESSING, SUCCESS, FAILURE, RECYCLE;

        public static StatusEnum convert(BuildResult buildResult, String optionType) {
            switch (buildResult) {
                case SUCCESS:
                    if (JenkinsParams.JenkinsStage.RECYCLE.getName().equals(optionType)) {
                        return RECYCLE;
                    }
                    return SUCCESS;
                case FAILURE:
                case UNSTABLE:
                case ABORTED:
                case UNKNOWN:
                case CANCELLED:
                    return FAILURE;
                default:
                    return PROCESSING;
            }
        }
    }
}
