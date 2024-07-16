import service.QuotaManagementService;
import service.impl.QuotaManagementServiceImpl;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        QuotaManagementServiceImpl quotaManagementService=new QuotaManagementServiceImpl();

        while (true){
            System.out.println(quotaManagementService.getRequest(1));
            Thread.sleep(10);
            System.out.println(quotaManagementService.getRequest(5));
            Thread.sleep(10);
        }

    }
}