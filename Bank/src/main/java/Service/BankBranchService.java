package Service;

import Entity.BankBranch;
import Entity.TypeUser;
import Repository.BankBranchRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class BankBranchService {
    private BankService bankService = new BankService();
    private BankBranchRepository bankBranchRepository = new BankBranchRepository();
    private LoginService loginService = new LoginService();
    private Scanner input = new Scanner(System.in);
    private String nameBank,bossFullName,nationalId,password,codeBranch;

    public BankBranchService() throws SQLException, ClassNotFoundException {
    }

    //::::>
    public void addBankBranch() throws SQLException {
        System.out.print("Enter bank name:");
        nameBank = input.nextLine();
        if( bankService.findBankName(nameBank) == 1 ) {
            System.out.println("This name Bank you enter doesn't Exists!");
            return;
        }
        System.out.print("Enter code Branch:");
        codeBranch = input.nextLine();
        if( bankBranchRepository.find(codeBranch) == 1 ){
            System.out.println("This code Branch already Exists!");
            return;
        }
        System.out.print("Enter boss full name:");
        bossFullName = input.nextLine();
        while(true){
            System.out.print("Enter your national Id:");
            nationalId = input.nextLine();
            if( loginService.findNationalId(nationalId) == 1 ) {
                System.out.println("you enter a wrong national id");
                continue;
            }
            else
                break;
        }
        System.out.println("Enter password for " + bossFullName );
        password = input.nextLine();
        loginService.addNewLogin(nationalId,password, TypeUser.BOSS);
        BankBranch newBankBranch = new BankBranch(nameBank,codeBranch,bossFullName,nationalId,password);
        bankBranchRepository.add(newBankBranch);
    }






}//
