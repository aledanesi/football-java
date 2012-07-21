/*
 * Progetto, a new java application 
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Rattazzi Street, Fifth Floor
 * Pomezia, RM  00040  Italy
 */
package com.progetto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author C_ICTDNS
 *
 */
@Entity 
@Table(name="ROLE") 
public class Role implements Serializable 
{
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)     
  @Column(name="ID", unique=true, nullable=false)    
  private int id;     
  
  @Column(name="name", nullable=false) 
  private String name;      
  
  @OneToOne     
  @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)       
  private User user;      
  
   
  public int getId() 
  {         
    return id;     
  }     
  
  public void setId(int id) 
  {         
    this.id = id;     
  }      
  
    
  public String getName() 
  {         
    return name;     
  }     
  
  public void setName(String name) 
  {         
    this.name = name;     
  }      
  
  public User getUser() 
  {         
    return user;     
  }      
  
  public void setUser(User user) 
  {         
    this.user = user;     
  }  
}