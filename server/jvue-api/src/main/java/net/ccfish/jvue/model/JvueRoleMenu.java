/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the jvue_role database table.
 * 
 */
@Entity
@Table(name="jvue_role_menu")
@NamedQuery(name="JvueRoleMenu.findAll", query="SELECT j FROM JvueRoleMenu j")
public class JvueRoleMenu {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="role_id")
    private JvueRole role;
    
    @ManyToOne
    @JoinColumn(name="menu_id")
    private JvueMenu menu;

    public JvueRoleMenu() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JvueRole getRole() {
        return role;
    }

    public void setRole(JvueRole role) {
        this.role = role;
    }

    public JvueMenu getMenu() {
        return menu;
    }

    public void setMenu(JvueMenu menu) {
        this.menu = menu;
    }

}
