package entity;

import entity.Firm;
import entity.Order;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T10:58:59")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Firm> firm;
    public static volatile SingularAttribute<History, Long> id;
    public static volatile SingularAttribute<History, Date> paymentDate;
    public static volatile SingularAttribute<History, Date> takeOn;
    public static volatile SingularAttribute<History, Order> order;

}