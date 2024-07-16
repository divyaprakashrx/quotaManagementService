package model;

import enums.Subscription;

public class User {
    int id;
    PersonalDetails details;
    Subscription subscription;

    public User(int id, PersonalDetails details, Subscription subscription){
        this.id = id;
        this.details = details;
        this.subscription = subscription;
    }

    public Subscription getSubscriptionType(int id){
        return subscription;
    }

}
