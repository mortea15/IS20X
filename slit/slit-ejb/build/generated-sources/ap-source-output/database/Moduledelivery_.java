package database;

import database.Module;
import database.ModuledeliveryPK;
import database.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-11T10:35:56")
@StaticMetamodel(Moduledelivery.class)
public class Moduledelivery_ { 

    public static volatile SingularAttribute<Moduledelivery, String> moduleAssesmentComment;
    public static volatile SingularAttribute<Moduledelivery, String> moduleDelivery;
    public static volatile SingularAttribute<Moduledelivery, Date> uploadDate;
    public static volatile SingularAttribute<Moduledelivery, Boolean> moduleStatus;
    public static volatile SingularAttribute<Moduledelivery, ModuledeliveryPK> moduledeliveryPK;
    public static volatile SingularAttribute<Moduledelivery, Module> module;
    public static volatile SingularAttribute<Moduledelivery, Users> users;

}