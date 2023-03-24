package io.kenxue.devops.application.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 部署节点配置
 *
 * @author haolongz@fiture.com
 * @date 3/11/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ClusterServerEnus {
    SINGLE("fastwave_dev_master", "192.168.143.134"), DOUBLE("fastwave_dev_master,fastwave_dev_slave1",
        "192.168.143.134,192.168.143.133"), QUAD("fastwave_dev_master,fastwave_dev_slave1",
        "192.168.143.134,192.168.143.133");
    private String allocation;
    private String ip;
}
