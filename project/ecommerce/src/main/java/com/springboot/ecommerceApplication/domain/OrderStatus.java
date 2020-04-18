package com.springboot.ecommerceApplication.domain;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.springboot.ecommerceApplication.domain.OrderStatus.To_Status.DELIVERED;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ORDER_PRODUCT_ID;
    private To_Status status;
    private From_Status status1;
    public enum To_Status {
        ORDERED,
        READY,
        DELIVERED,
        CANCELLED,
        RETURN_REQUEST,
        RETURN_APPROVED,
        RETURN_REJECTED;

                 }
    public enum From_Status {
        ORDERED,
        READY,
        DELIVERED,
        CANCELLED,
        RETURN_REQUEST,
        RETURN_APPROVED,
        RETURN_REJECTED;
    }

    //An order can be cancelled as long as it is not delivered.
                 public boolean isCanceable() {
                 if (getStatus() ==To_Status.DELIVERED) {
                 return true;
                }
                return false; }
              //An order can be returned or requested for refund once it is delivered
        public boolean isRefundable(){
             if(getStatus()==To_Status.DELIVERED){
               return true;
            }
              return false; }

    public int getORDER_PRODUCT_ID() {
        return ORDER_PRODUCT_ID;
    }

    public void setORDER_PRODUCT_ID(int ORDER_PRODUCT_ID) {
        this.ORDER_PRODUCT_ID = ORDER_PRODUCT_ID;
    }

    public To_Status getStatus() {
        return status;
    }

    public void setStatus(To_Status status) {
        this.status = status;
    }

    public From_Status getStatus1() {
        return status1;
    }

    public void setStatus1(From_Status status1) {
        this.status1 = status1;
    }
}

