package cn.colablog.demo.ioc.overview.domain;

import cn.colablog.demo.ioc.overview.annotation.Super;

/**
 * @author Johnson
 * @date 2020/07/10/ 17:38:04
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
