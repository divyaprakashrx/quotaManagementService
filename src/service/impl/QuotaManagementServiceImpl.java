package service.impl;

import enums.Subscription;
import model.PersonalDetails;
import model.User;
import model.UtilisedData;
import service.QuotaManagementService;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class QuotaManagementServiceImpl implements QuotaManagementService {
    HashMap<Integer, UtilisedData> utilisedQuota=new HashMap<>();
    HashMap<Integer, User> userMap=new HashMap<>();
    private Integer FreeLimit=10;
    private  Integer PremiumLimit=500;

    public QuotaManagementServiceImpl(){
        for(int i = 0; i<3; i+=1){
            User newUser=new User(i,new PersonalDetails(),Subscription.FREE);
            userMap.put(i,newUser);
        }
        for(int i = 3; i<6; i+=1){
            User newUser=new User(i,new PersonalDetails(),Subscription.PREMIUM);
            userMap.put(i,newUser);
        }
    }

    @Override
    public String getRequest(int id) {
        LocalTime currentTime=LocalTime.now();
        if(userMap.containsKey(id)){
            if(utilisedQuota.containsKey(id)){
            UtilisedData currentQuota=utilisedQuota.get(id);
            if(ChronoUnit.MINUTES.between(currentQuota.getTime(),currentTime)<1){
                    Subscription userSubscription=userMap.get(id).getSubscriptionType(id);
                    if(userSubscription==Subscription.FREE){
                        if(currentQuota.getCount()<FreeLimit){
                            currentQuota.increaseCount();
                            return "API Call Success; Subscription Type: "+userSubscription;
                        }
                        else{
                            return "Quota Limit Exceeded; Subscription Type: "+userSubscription;
                        }
                    }
                    if(userSubscription==Subscription.PREMIUM){
                        if(currentQuota.getCount()<PremiumLimit){
                            currentQuota.increaseCount();
                            return "API Call Success; Subscription Type: "+userSubscription;
                        }
                        else{
                            return "Quota Limit Exceeded; Subscription Type: "+userSubscription;
                        }
                    }
            }
            else{
                currentQuota.setTime(currentTime);
                currentQuota.resetCount();
                currentQuota.increaseCount();
                return "API Successful; Subscription Type: "+Subscription.PREMIUM;
            }
        }
        else{
            UtilisedData newData=new UtilisedData(id);
            newData.increaseCount();
            utilisedQuota.put(id,newData);
            return "API Successful";
        }

        }
        else{
            return "User Not Found";
        }
        return "";
    }
}
