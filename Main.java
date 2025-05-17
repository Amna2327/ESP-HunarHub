import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main{ //MAIN CLASS
    public static void main(String args[]){
       Scanner input=new Scanner(System.in);
       ArrayList<Maker>MakerList=new ArrayList<>();
       ArrayList<Buyer>BuyerList=new ArrayList<>();
       ArrayList<Item> ItemList=new ArrayList<>();
       ArrayList<Course> CourseList = new ArrayList<>();
       ArrayList<Order> OrderList = new ArrayList<>();
       ArrayList<Admin> AdminList = new ArrayList<>();
       
       CourseManager cm = new CourseManager(CourseList);
       Admin a1 = new Admin("Zainab","password1",cm);
       Admin a2 = new Admin("Amna","password2",cm);
       Admin a3 = new Admin("Eisha","password3",cm);
       Admin a4 = new Admin("Saad","password4",cm);
       AdminList.add(a1);
       AdminList.add(a2);
       AdminList.add(a3);
       AdminList.add(a4);

       File Courses = new File("Courses.txt");
       try{
           Courses.createNewFile();
       }
       catch(IOException e){
           System.out.println(e);
       }
       if(Courses.length() != 0){
           FileHandlingforCourses.readFile(CourseList);
       }
       
       
       File Makers=new File("Makers.txt");
       try{
        Makers.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Makers.length()!=0){
         FileHandlingforMaker.readFile(MakerList);
       }
       
       File Items=new File("Item.txt");
       try{
        Items.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Items.length()!=0){
         ItemList=FilehanddlingforItem.readFile(MakerList);
       }

       File Buyers=new File("Buyers.txt");
       try{
        Buyers.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Buyers.length()!=0){
         FileHandlingforBuyer.readFile(BuyerList);
       }
       for (Buyer b : BuyerList) {
            b.setItemList(ItemList);
            b.setMakerList(MakerList);
            b.setBuyerList(BuyerList);
        }
       
       File Wishlist=new File("WishList.txt");
       try{
            Wishlist.createNewFile();
       }
       catch(IOException e){
            System.out.println(e);
       }
       if(Wishlist.length()!=0){
            FileHandlingforWishList.readFile(BuyerList);
       }
       
       File Cart=new File("Cart.txt");
       try{
            Cart.createNewFile();
       }
       catch(IOException e){
            System.out.println(e);
       }
       if(Cart.length()!=0){
            FileHandlingforCart.readFile(BuyerList);
       }
       
       File Order=new File("Orders.txt");
       try{
            Order.createNewFile();
       }
       catch(IOException e){
            System.out.println(e);
       }
       if(Order.length()!=0){
            FileHandlingForOrders.readFile(OrderList, BuyerList, ItemList);
       }
       
       File Reviews=new File("Reviews.txt");
       try{
        Reviews.createNewFile();
       }
       catch(IOException e){
         System.out.println(e);
       }
       if(Reviews.length()!=0){
         FilehandlingforReviews.readFile(ItemList);
       }
      
       Management m1=new Management();
       m1.Manage(input,MakerList,BuyerList,ItemList, AdminList);
    }
}

class FileHandlingforMaker{
 
  public static void readFile(ArrayList<Maker>MakerList){
    try{
        BufferedReader reader=new BufferedReader(new FileReader("Makers.txt"));
        String line;
        while((line=reader.readLine())!=null){
            String[] data=line.split(",");
            if (data.length >= 5) { // Ensure there are at least 5 elements
                Maker m = new Maker(data[0], data[1], data[2], data[3], data[4]);
                MakerList.add(m);
            } else {
                System.out.println("Skipping malformed line: " + line);
            }

        }
        reader.close();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
   // super(userId, name, email, password,bankPassword);
  public static void writeFile(Maker m){
    try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Makers.txt",true));
        writer.write(m.getUserId()+","
                     +m.getName()+","
                     +m.getEmail()+","
                     +m.password+","
                     +m.bankPassword+"\n");
        writer.close();
    }
    catch(IOException e){
        System.out.println(e);
    }
  }
}

class FileHandlingforBuyer{
 
  public static void readFile(ArrayList<Buyer>BuyerList){
    try{
        BufferedReader reader=new BufferedReader(new FileReader("Buyers.txt"));
        String line;
        while((line=reader.readLine())!=null){
            String[] data=line.split(",");
            if (data.length >= 6) { // Ensure there are at least 6 elements
                Buyer m = new Buyer(data[0], data[1], data[2], data[3], data[4]);
                BankAccount acc = new BankAccount(data[4], Double.parseDouble(data[5]));
                m.bankAccount = acc;
                BuyerList.add(m);
            } else {
                System.out.println("Skipping malformed line: " + line);
            }

        }
        reader.close();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
   // super(userId, name, email, password,bankPassword);
  public static void writeFile(Buyer m){
    try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Buyers.txt",true));
        writer.write(m.getUserId()+"," + m.getName()+"," + m.getEmail() + "," +
        m.password+"," + m.bankPassword+"," + m.bankAccount.getBalance()+ "\n");
        writer.close();
    }
    catch(IOException e){
        System.out.println(e);
    }
  }
  public static void rewriteFile(ArrayList<Buyer> buyerlist){
    try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("Buyers.txt",false));
        for(Buyer m:buyerlist){
            writer.write(m.getUserId()+"," + m.getName() + "," + m.getEmail() + "," +
            m.password+"," + m.bankPassword+"," + m.bankAccount.getBalance()+ "\n");
        }
        writer.close();
    }
    catch(IOException e){
        System.out.println(e);
    }
  }
}

class FilehandlingforReviews{

    public static void readFile(ArrayList<Item>ItemList){
        try{//Review(String reviewId,String reviewerName, Item item, int rating, String comment,String reviewerId) {
            BufferedReader reader=new BufferedReader(new FileReader("Reviews.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] data=line.split(",");
                Review r=new Review( data[0],data[1],data[2],Integer.parseInt(data[3]),data[4],data[5]);
                for(int i=0;i<ItemList.size();i++){
                    if(ItemList.get(i).getItemId().equals(data[2]))
                        ItemList.get(i).addReview(r);
                }
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void writeFile(Review review){
        try{
          BufferedWriter writer=new BufferedWriter(new FileWriter("Reviews.txt",true));
          writer.write(review.getReviewId()+","
                       +review.getReviewerName()+","
                       +review.getItemId()+","
                       +review.getRating()+","
                       +review.getComment()+","
                       +review.getReviewerId()+"\n");
          writer.close();
        }
        catch(IOException e){

        }
    }
}

class FileHandlingforCourses{
    public static void readFile(ArrayList<Course>CourseList){
        try{//Review(String reviewId,String reviewerName, Item item, int rating, String comment,String reviewerId) {
            BufferedReader reader=new BufferedReader(new FileReader("Courses.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] data=line.split(",");
                Course c =new Course(data[0],data[1],data[2]);
                CourseList.add(c);
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void writeFile(Course course){
        try{
          BufferedWriter writer=new BufferedWriter(new FileWriter("Courses.txt",true));
          writer.write(course.getTitle()+","+course.getCategory()+","+course.getDescription()+"\n");
          writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void rewriteFile(ArrayList<Course>CourseList){
        //rewrite file without a course/with changed details by taking reference form the course arraylist
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("Courses.txt",false));
            for(Course course:CourseList){
                writer.write(course.getTitle()+","+course.getCategory()+","+course.getDescription());
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
class FileHandlingforWishList{
    public static void readFile(ArrayList<Buyer>BuyerList){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("WishList.txt"));
            String line;
            while( (line = reader.readLine()) != null){
                String pieces[]=line.split(",");
                String userID = pieces[0];
                String uName = pieces[1];
                String uEmail = pieces[2];
                String itemID = pieces[3];
                String title = pieces[4];
                Double price = Double.parseDouble(pieces[5]);
                String makerID = pieces[6];
                String makerName = pieces[7];
                String category = pieces[8];
                String desc = pieces[9];
                int stock = Integer.parseInt(pieces[10]);
                
                Item i = new Item(itemID,title,desc,price,makerName,category,makerID,stock);
                WishList w = null; //crete items and ass list to wishlist of buyer
                Buyer b = null; //search for buyer and populate its wishlist
 
                //b.getUserId()+","+b.getName()+","+b.getEmail()+itemId + "," + title + "," + price + "," +makerId+","+makerName+","+category+","+description+","+Stock;
    
                for(Buyer buyer:BuyerList){
                    if(pieces[0].equals(buyer.getUserId())){
                        b = buyer;
                        break;
                    }
                }
                if(b!= null)
                    b.addItemfromFile(i);
                else
                    System.out.println("Buyer with ID " + userID + " not found.");
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void writeFile(String w){
        try{
          BufferedWriter writer=new BufferedWriter(new FileWriter("WishList.txt",true));
          writer.write(w);
          //b.getUserId()+","+b.getName()+","+b.getEmail()+","itemId + "," + title + "," + price + "," +makerId+","+makerName+","+ category+","+description;
          writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void rewriteFile(ArrayList<Buyer>BuyerList){
        //rewrite file with changed wishlist by taking reference form the buyer arraylist
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("WishList.txt",false));
            for(Buyer b:BuyerList){
                String list = b.generateItemList();
                if(!list.isEmpty()) //writes list only when its NOT empty i.e. ""
                    writer.write(list);
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
class FileHandlingforCart{
    public static void readFile(ArrayList<Buyer>BuyerList){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("Cart.txt"));
            String line;
            while( (line = reader.readLine()) != null){
                String pieces[]=line.split(",");
                String userID = pieces[0];
                String uName = pieces[1];
                String uEmail = pieces[2];
                String itemID = pieces[3];
                String title = pieces[4];
                Double price = Double.parseDouble(pieces[5]);
                String makerID = pieces[6];
                String makerName = pieces[7];
                String category = pieces[8];
                String desc = pieces[9];
                int stock = Integer.parseInt(pieces[10]);
                
                Item i = new Item(itemID,title,desc,price,makerName,category,makerID,stock);
                WishList w = null; //crete items and add list to wishlist of buyer
                Buyer b = null; //search for buyer and populate its wishlist
 
                //b.getUserId()+","+b.getName()+","+b.getEmail()+itemId + "," + title + "," + price + "," +makerId+","+makerName+","+category+","+description+","+Stock;
    
                for(Buyer buyer:BuyerList){
                    if(pieces[0].equals(buyer.getUserId())){
                        b = buyer;
                        break;
                    }
                }
                if(b!= null)
                    b.addItemfromFile(i);
                else
                    System.out.println("Buyer with ID " + userID + " not found.");
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void writeFile(String w){
        try{
          BufferedWriter writer=new BufferedWriter(new FileWriter("Cart.txt",true));
          writer.write(w);
          //b.getUserId()+","+b.getName()+","+b.getEmail()+","itemId + "," + title + "," + price + "," +makerId+","+makerName+","+ category+","+description;
          writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void rewriteFile(ArrayList<Buyer>BuyerList){
        //rewrite file with changed wishlist by taking reference form the buyer arraylist
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("Cart.txt",false));
            for(Buyer b:BuyerList){
                String list = b.generateItemList();
                if(!list.isEmpty()) //writes list only when its NOT empty i.e. ""
                    writer.write(list);
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}

class FilehanddlingforItem{
    static ArrayList<Item>ItemList=new ArrayList<>();
    
    public static ArrayList readFile(ArrayList<Maker>MakerList) {
       try{
         BufferedReader reader=new BufferedReader(new FileReader("Item.txt"));
         String line;
         while((line=reader.readLine())!=null){
           String pieces[]=line.split(",");
           Item item=new Item(pieces[0],pieces[1],pieces[2],Double.parseDouble(pieces[3]),pieces[4],pieces[5],pieces[6],Integer.parseInt(pieces[7]));
           ItemList.add(item);
         }
         reader.close();
       }
       catch(Exception e){
        System.out.println(e);
       }

       for(int i=0;i<MakerList.size();i++){
        for(int j=0;j<ItemList.size();j++){
           if(ItemList.get(j).getMakerID().equals(MakerList.get(i).getUserId()))
              MakerList.get(i).AddITEM(ItemList.get(j));
        }
       }
       return ItemList;
    }
    
    public static void  writeFile(Item item, String purpose){
        if(purpose.equals("add")){
            ItemList.add(item);
            try{
              FileWriter writer=new FileWriter("Item.txt",true);
              
                writer.write( ItemList.get(ItemList.size()-1).getItemId() + "," +
                              ItemList.get(ItemList.size()-1).getTitle() + "," +
                              ItemList.get(ItemList.size()-1).getDescription() + "," +
                              ItemList.get(ItemList.size()-1).getPrice() + "," +
                              ItemList.get(ItemList.size()-1).getMakerName() + "," +
                              ItemList.get(ItemList.size()-1).getCategory() + "," +
                              ItemList.get(ItemList.size()-1).getMakerID() + "," +
                              ItemList.get(ItemList.size()-1).getStock() + "\n"); // Add newline for each item
                              writer.close();
            }
            catch(IOException e){
               System.out.println(e);
            }
        }
        else if(purpose.equals("remove")){
          ItemList.remove(item);
         
            try{
              FileWriter writer=new FileWriter("Item.txt");
              for (Item it : ItemList) {
                writer.write(it.getItemId() + "," +
                             it.getTitle() + "," +
                             it.getDescription() + "," +
                             it.getPrice() + "," +
                             it.getMakerName() + "," +
                             it.getCategory() + "," +
                             it.getMakerID() + "," +
                             it.getStock() + "\n"); // Add newline for each item
                }
                writer.close();
                System.out.println("Your item has been removed");
            }
            catch(IOException e){
            System.out.println(e);
            }
         
        }
        
        else if(purpose.equals("modify")){
            int index=0;
           for(int i=0;i<ItemList.size();i++){
             if(ItemList.get(i).getItemId().equals(item.getMakerID()))
               index=i;
           }
          ItemList.remove(index);//removed old version of the item
          ItemList.add(item);//added modified version
         
            try{
              FileWriter writer=new FileWriter("Item.txt");
              for (Item it : ItemList) {
                writer.write(it.getItemId() + "," +
                             it.getTitle() + "," +
                             it.getDescription() + "," +
                             it.getPrice() + "," +
                             it.getMakerName() + "," +
                             it.getCategory() + "," +
                             it.getMakerID() + "," +
                             it.getStock() + "\n"); // Add newline for each item
                }
                writer.close();
                System.out.println("Your item has been modified");
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }
}   
class FileHandlingForOrders {
    public static void readFile(ArrayList<Order> OrderList, ArrayList<Buyer> buyerlist, ArrayList<Item> itemlist){
        try{
            BufferedReader reader=new BufferedReader(new FileReader("Orders.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] data = line.split(",");
                String buyerID = data[1];
                String itemID = data[2];
                Buyer b = null;
                for(Buyer currentbuyer:buyerlist){
                    if(currentbuyer.getUserId().equals(buyerID)){
                        b = currentbuyer;
                        break;
                    }
                }
                Item i = null;
                for(Item currentitem:itemlist){
                    if(currentitem.getItemId().equals(itemID)){
                        i = currentitem;
                        break;
                    }
                }
                if(b == null || i ==null)
                    System.out.print("Buyer or Item not found, terminating order history");
                else{
                    Order order = new Order(data[0], b, i);
                    OrderList.add(order);
                }
               
            }
            reader.close();
        }
        catch(IOException e){
          System.out.println(e);
        }
    }
    public static void writeFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Orders.txt", true))) {
            // Assuming Order has getOrderId(), getItem(), getBuyer()
            writer.write(order.getOrderId() + "," +
            order.getBuyer().getUserId() + "," + order.getItem().getItemId() + "," +
            order.getItem().getTitle() + "," + order.getItem().getPrice() + ","+
            order.getItem().getMakerID()+ ","+order.getItem().getMakerName()+"\n");
        } catch (IOException e) {
            System.out.println("Error writing order to file: " + e.getMessage());
        }
    }
}

class Management{//class responsible for showing intial choice menu
    void Manage(Scanner input,ArrayList<Maker>MakerList,ArrayList<Buyer>BuyerList,ArrayList<Item> itemlist, ArrayList<Admin> devs){
        System.out.println("\n=== Welcome to HunarHub ===");
        boolean continueLoop1=true;
        boolean continueLoop2=false;
        int choice=0;
        do{//loop for choices
         System.out.println("Choose one of the following");
         System.out.println("1.To login");
         System.out.println("2.To sign up");
         System.out.println("3.Exit");
         do{
             try{//for valid input
                 choice=input.nextInt();
                 if(choice!=1&&choice!=2&&choice!=3){//throws custom exception
                     continueLoop2=true;
                     throw new WrongInput("Please choose bwtween 1 and 3");
                 }
                 else 
                 continueLoop2=false;
             }
             catch(WrongInput e){
                 input.nextLine();
                 System.out.println(e);
             }
             catch(InputMismatchException e){//catches exception if input was not integer
                 input.nextLine();
                 System.out.println("Error: Please enter a valid integer between 1 and 3");
                 continueLoop2=true;
             }
         }while(continueLoop2);
         switch(choice){
             case 1:
                System.out.println("Choose your user account type");
                System.out.println("1.Maker");
                System.out.println("2.Buyer");
                System.out.println("3.Admin");
                int AccountType=input.nextInt();
                while(AccountType!=1&&AccountType!=2&&AccountType!=3){
                    System.out.println("Wrong input, please enter again");
                    AccountType=input.nextInt();
                }
               
                Login login=new Login();
                if(AccountType==1)
                    login.LoginMenuMaker(MakerList,input);
                else if(AccountType == 2)
                    login.LoginMenuBuyer(BuyerList,input);
                else
                    login.LoginMenuAdmin(devs,input);
                break;

             case 2:
                Signup signup=new Signup();
                signup.SignUpMenu(input,MakerList,BuyerList,itemlist);
                break;

             case 3:
                continueLoop1=false;
                break;
         }
 
        }while(continueLoop1);
        //input.close();
        /*Never call .close() on a Scanner created from System.in if you plan to use it again.
        Because System.in is a shared stream, closing it makes it unusable for the entire runtime of the application.*/
    }
}
class Login{//for login
   void LoginMenuMaker(ArrayList<Maker>MakerList,Scanner input){
       System.out.println("--- Login ----");
       System.out.println("Enter password");
       boolean passwordExists=false;
       Maker A=null;//empty user refernce
       input.nextLine();
       do{
        try{
            String password=input.nextLine();
            for(Maker a:MakerList){
                if(password.equals(a.password)){
                   A=a;//points to the user in question
                   passwordExists=true;
                   break;
                }
            }
            if(passwordExists==false)
              throw new WrongInput("Entered password is wrong");
        }
        catch(WrongInput e){
            System.out.println(e);
        }
       }while(!passwordExists);
            A.showMenu();;
        return;
    }
    void LoginMenuBuyer(ArrayList<Buyer>BuyerList,Scanner input){
       System.out.println("--- Login ----");
       System.out.println("Enter password");
       boolean passwordExists=false;
       User A=null;//empty user refernce
       input.nextLine();
       do{
        try{
            String password=input.nextLine();
            for(Buyer a:BuyerList){
                if(password.equals(a.password)){
                   A=a;//points to the user in question
                   passwordExists=true;
                   break;
                }
            }
            if(passwordExists==false)
              throw new WrongInput("Entered password is wrong");
        }
        catch(WrongInput e){
            System.out.println(e);
        }
       }while(!passwordExists);
        A.showMenu();;
        return;
    }
    void LoginMenuAdmin(ArrayList<Admin> devs,Scanner input){
        System.out.println("--- Admin Login ----");
        System.out.print("\nEnter password:");
        boolean passwordExists=false;
        Admin admin=null;//empty user refernce
        input.nextLine();
        do{
         try{
             String password=input.nextLine();
             for(Admin a:devs){
                 if(password.equals(a.getPassword())){
                    admin=a;//points to the user in question
                    passwordExists=true;
                    break;
                 }
             }
             if(passwordExists==false)
               throw new WrongInput("Entered password is wrong");
         }
         catch(WrongInput e){
             System.out.println(e);
         }
        }while(!passwordExists);
         admin.showMenu();
         return;
     }
}
class Signup{//for signup
    void SignUpMenu(Scanner input,ArrayList<Maker>MakerList,ArrayList<Buyer>BuyerList,ArrayList<Item> itemlist){

      boolean continueLoop=true;
      boolean UserNameExists=false;
      String Username=null;
      String accountPassword=null;
      String bankPassword=null;

      System.out.println("\n--- Signup ----");
      System.out.print("\nEnter a User name:");
      input.nextLine();
      do{
        try{
            Username =input.nextLine();
            for(User a:MakerList){
               if(Username.equals(a.getName())){
                 UserNameExists=true;
                 break;
               }
            } 
            for(User a:BuyerList){
               if(Username.equals(a.getName())){
                 UserNameExists=true;
                 break;
               }
            } 
            if(UserNameExists==true)
              throw new WrongInput("This user name already exists, Please add some characters to it to make it unique");
            continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println(e);
            UserNameExists=false;//resetting
        }
      }while(continueLoop);

       System.out.print("Enter email: ");
       String email = input.nextLine();

      continueLoop=true;//resetting
      boolean hasSpecialCharacter=false;
      System.out.println("Enter a Strong password for your account"); 
      System.out.println("It must have atleast 8 characters with atleast one special character");
      do{
        try{
          accountPassword=input.nextLine();
          if(accountPassword.length()<8)
            throw new WrongInput("Password must atleast be 8 characters long, enter again");
          for(int i=0;i<accountPassword.length();i++){
            if((int)accountPassword.charAt(i)<=47&&(int)accountPassword.charAt(i)>=33||(int)accountPassword.charAt(i)<=64&&(int)accountPassword.charAt(i)>=58||(int)accountPassword.charAt(i)<=96&&(int)accountPassword.charAt(i)>=91||(int)accountPassword.charAt(i)<=126&&(int)accountPassword.charAt(i)>=123){
                hasSpecialCharacter=true;
                break;
            }
           }
           if(hasSpecialCharacter==false)
             throw new WrongInput("Password must contain atleast one special character, enter again");
           continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println( e);
        }
      }while(continueLoop);

      continueLoop=true;//resetting
      hasSpecialCharacter=false;//resetting
      System.out.println("You will need to create a bank account");
      System.out.println("For this purpose,Enter a strong password for your account");
      System.out.println("It must have atleast 8 characters with atleast one special character");
      do{
        try{
          bankPassword=input.nextLine();
          if( bankPassword.length()<8)
            throw new WrongInput("\nPassword must atleast be 8 characters long, enter again");
          for(int i=0;i<bankPassword.length();i++){
            if((int) bankPassword.charAt(i)<=47&&(int)bankPassword.charAt(i)>=33||(int)bankPassword.charAt(i)<=64&&(int)bankPassword.charAt(i)>=58||(int)bankPassword.charAt(i)<=96&&(int)bankPassword.charAt(i)>=91||(int)bankPassword.charAt(i)<=126&&(int)bankPassword.charAt(i)>=123){
                hasSpecialCharacter=true;
                break;
            }
           }
           if(hasSpecialCharacter==false)
             throw new WrongInput("Password must contain atleast one special character, enter again");
           continueLoop=false;
        }
        catch(WrongInput e){
            System.out.println( e);
        }
      }while(continueLoop);

        System.out.println("Lastly, choose your account type");
        System.out.println("Choose your user account type");
        System.out.println("1.Maker");
        System.out.println("2.Buyer");
        int AccountType=input.nextInt();
        while(AccountType!=1&&AccountType!=2){
          System.out.println("Wrong input, please enter again");
          AccountType=input.nextInt();
        }

        String userId = "U" + (MakerList.size() + BuyerList.size() + 1);
        //public User(String userId, String name, String email, String password,String bankPassword)
        if(AccountType==1){
           Maker m=new Maker(userId, Username,email,accountPassword,bankPassword);
           MakerList.add(m);
           FileHandlingforMaker.writeFile(m);
          return;
        }
        else{
           Buyer b=new Buyer(userId, Username,email,accountPassword,bankPassword);
           b.setMakerList((List)MakerList);
           b.setItemList((List)itemlist);
           b.setBuyerList(BuyerList);
           BuyerList.add(b);
           FileHandlingforBuyer.writeFile(b);
          return;
        }
    }
}
class WrongInput extends Exception{
    String message;
    WrongInput(String message){
      this.message = message;
    }
    public String toString(){
       return "Error: " + message;
    }
}

 class Maker extends User {
    private List<Item> items;

    public Maker(String userId, String name, String email, String password, String bankPassword) {
        super(userId, name, email, password,bankPassword);
        this.items = new ArrayList<>();
    }

    // Add product
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added successfully.");
        FilehanddlingforItem.writeFile(item,"add");
    }

    //method when reading from file
    public void AddITEM(Item item){
        items.add(item);
    }

    // View all products added by this maker
    public void viewMyItems() {
        if (items.isEmpty()) {
            System.out.println("You have not added any items yet.");
        } else {
            int counter=1;
            System.out.println("Your Items:");
            for (Item item : items) {
                System.out.println(counter+". "+item);
                counter++;
            }
        }
    }

    // View account balance
    public void viewAccountBalance() {
        System.out.println("Current Balance: Rs. " + bankAccount.getBalance());
    }

    //remove product
    public void RemoveProduct(){
      Scanner scanner=new Scanner(System.in);
      viewMyItems();
      if (items.isEmpty()) {
//        // scanner.close();
         return;
      }

      System.out.println("Enter item serial number");
      int index=scanner.nextInt()-1;
      if(index<0||index>items.size()){
        System.out.println("Wrong serial number");
//        //scanner.close();
        return;
      }
      FilehanddlingforItem.writeFile(items.get(index),"remove");
      items.remove(index);
////      scanner.close();
    }

    //modify product details
    public void ModifyProduct(){
      Scanner scanner=new Scanner(System.in);
      viewMyItems();
      int index=-1;
      if (items.isEmpty()) {
////        scanner.close();
        return;
      }

      System.out.println("Enter item serial number");
      index=scanner.nextInt()-1;
      if(index<0||index>items.size()){
        System.out.println("Wrong serial number");
////        scanner.close();
        return;
      }
      System.out.println("Choose one of the following");
      System.out.println("1.Modify Title");
      System.out.println("2.Modify Price");
      System.out.println("3.Modify Description");
      System.out.println("4.Modify Quantity");

      int choice=scanner.nextInt();
      while(choice!=1&&choice!=2&&choice!=3&&choice!=4){
        System.out.println("Invalid input,please enter again");
        choice=scanner.nextInt();
      }
      scanner.nextLine();

      if(choice==1){
        System.out.print("Enter item new title: ");
        items.get(index).setTitle(scanner.nextLine());
      }
      else if(choice==2){
        System.out.print("Enter item new price: ");
        items.get(index).setPrice(scanner.nextDouble());
      }
      else if(choice==3){
        System.out.print("Enter item new description: ");
        items.get(index).setDescription(scanner.nextLine());
      }
      else{
        System.out.print("Enter item updated stock quantity: ");
        items.get(index). setStock(scanner.nextInt());
      }
        FilehanddlingforItem.writeFile(items.get(index),"modify");
////        scanner.close();
    }
      
    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Maker Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove product");
            System.out.println("3. Modify existing product details");
            System.out.println("4. View My Products");
            System.out.println("5. View Account Details");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    ArrayList<String> CategoryList=new ArrayList<>(Arrays.asList("Ceramics","Brass and Copper Utensils","Wood Carving",
                            "Marble Carving","Blue Pottery","Ajrak","Khussa","Truck Art","Pashmina Shawls","Basketry","Embroidered Shawls"));
                    System.out.print("Enter item title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter item description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    System.out.println("Your handicraft should fit one of these categories");
                    int counter=0;
                    for(String a:CategoryList){
                        System.out.println((counter+1)+"." +a);
                        counter++;
                    }
                    System.out.print("\nEnter the category number of your product :");
                    int index=scanner.nextInt();
                    while(index<0&&index<CategoryList.size()){
                        System.out.println("Invalid choice, please enter again");
                        index=scanner.nextInt();
                    }
                    scanner.nextLine();
                    String category=CategoryList.get(index-1);

                    System.out.print("Enter available stock of your product:");
                    int Stock=scanner.nextInt();

                    String itemId = "I" + (items.size() + 1); // simple ID generation
                    Item item = new Item(itemId, title, desc, price,this.name,category,this.getUserId(),Stock);
                    addItem(item);
                    break;
                
                case 2:
                     RemoveProduct();
                     break;
                case 3:
                    ModifyProduct();
                    break;

                case 4:
                    viewMyItems();
                    break;

                case 5:
                    viewAccountBalance();
                    break;
                
                case 6:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (choice != 6);
////        scanner.close();
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public List<Item> getItems() {
        return items;
    }
}
class Course{
    private String title;
    private String category;
    private String description;
    
    Course(String title,String category,String description){
        this.title = title;
        this.category = category;
        this.description = description;
    }
    public String getTitle(){
        return this.title;
    }
    public String getCategory(){
        return this.category;
    }
    public String getDescription(){
        return this.description;
    }
    public void setTitle(String t){
        this.title = t;
    }
    public void setCategory(String c){
        this.category = c;
    }
    public void setDescription(String d){
        this.description = d;
    }
    @Override
    public String toString(){
        return "Title: "+this.title+" | Category: "+this.category + "\n Description:" + this.description;
    }
}
class CourseManager{
    private ArrayList<Course> courselist;
    
    CourseManager(ArrayList<Course> courselist){
        this.courselist = courselist;
    }
    public void addCourses(Course c){
        this.courselist.add(c);
        FileHandlingforCourses.writeFile(c);
    }
    public void removeCourses(Scanner input){
        Course c = null;
        this.viewCourses();
        int num;
        do{
            System.out.print("\nEnter the number of the course you want to remove:");
            num = input.nextInt()-1;
            if(num<0 || num>=courselist.size())
                System.out.println("Enter a valid number");
            else
                c = courselist.get(num);
        }while(c == null);
        courselist.remove(c);
        FileHandlingforCourses.rewriteFile(courselist);
    }
    public void viewCourses(){
        int i = 1;
        for(Course c:courselist){
            System.out.println("COURSE "+i+" ------------");
            System.out.println(c);
            i++;
        }
    }
    public void modifyDetails(Scanner input){
        Course c = null;
        this.viewCourses();
        int num;
        do{
            System.out.print("\nEnter the number of the course you want to modify:");
            num = input.nextInt()-1;
            if(num<0 || num>=courselist.size())
                System.out.println("Enter a valid number");
            else
                c = courselist.get(num);
        }while(c == null);
        
        int choice;
        String changed;
        boolean isExit = false;
        System.out.println("Select an attribute to modify: \n1) Title 2) Category 3) Description");
        
        do{
            System.out.print("\n Your Choice:");
            choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:{
                    System.out.print("\nEnter new Title:");
                    changed = input.nextLine();
                    c.setTitle(changed);
                    isExit = true;
                }break;
                case 2:{
                    System.out.print("\nEnter new Category:");
                    changed = input.nextLine();
                    c.setCategory(changed);
                    isExit = true;
                }break;
                case 3:{
                    System.out.print("\nEnter new Description:");
                    changed = input.nextLine();
                    c.setDescription(changed);
                    isExit = true;
                }break;
                default:
                        System.out.println("Invalid input");
                    break;
            }
        }
        while(isExit = false);
        //rewrite file with updated information
        FileHandlingforCourses.rewriteFile(courselist);
    }
    
}
class Admin{
    private CourseManager coursemanager;
    private String username;
    private String password;
    
    Admin(String username, String password, CourseManager cm){
        this.username = username;
        this.password = password;
        this.coursemanager = cm;
    }
    public String getPassword(){
        return this.password;
    }
    public void showMenu(){
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Courses");
            System.out.println("2. Remove Courses");
            System.out.println("3. Modify existing Course details");
            System.out.println("4. View All Courses");
            System.out.println("5. Issue Warnings");
            System.out.println("6. Remove Makers");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:{  //add courses 
                    System.out.print("\nEnter the Title of the course:");
                    String name = input.nextLine();
                    System.out.print("\nEnter the Category:");
                    String category = input.nextLine();
                    System.out.print("\nEnter the Description:");
                    String desc = input.nextLine();
                    Course c = new Course(name, category, desc);
                    coursemanager.addCourses(c);
                }break;
                case 2:{  //remove courses 
                    coursemanager.removeCourses(input);
                }break;
                case 3: //modify course details
                    coursemanager.modifyDetails(input);
                    
                    break;
                case 4: //view all course details
                    coursemanager.viewCourses();
                    break;
                case 5: //issue warnings to flagged makers
                    
                    break;
                case 6: //remove makers after scams
                    
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (choice != 7);
    }
}
 abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String bankPassword;
    protected BankAccount bankAccount;

    // Constructor: called when creating a Maker or Buyer
    public User(String userId, String name, String email, String password,String bankPassword) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankPassword=bankPassword;
        this.bankAccount=new BankAccount(bankPassword);
    }

    // Common getter methods (you can add setters if needed)
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // This is an abstract method. Maker and Buyer will implement this in their own way.
    public abstract void showMenu();
}
 class BankAccount {
    private double balance;
    private String password;

    public BankAccount(String password) {
        this.balance = 0.0;
        this.password=password;
    }
    public BankAccount(String password, Double balance) {
        this.balance = balance;
        this.password=password;
    }

    public String getAccountPassword(){
        return this.password;
    }
    public double getBalance() {
        return balance;
    }

    // Add money to account (e.g., after a buyer purchases an item)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Rs. " + amount + " successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from account (optional, may not be needed now)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew Rs. " + amount + " successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}
class Item {
    private String itemId;
    private String makerId;
    private String title;
    private String description;
    private String category;
    private double price;
    private String makerName;
    private int Stock;
    private List<Review> reviews = new ArrayList<>();


    public Item(String itemId, String title, String description, double price, String makerName,String category,String makerId, int Stock) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.makerName=makerName;
        this.category=category;
        this.makerId=makerId;
        this.Stock=Stock;
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getMakerName() {
        return makerName;
    }
    public String getMakerID(){
        return makerId;
    }
    public String getCategory(){
        return category;
    }

    public int getStock() {
        return Stock;
    }
     
    //setters
    void setTitle(String title){
       this.title=title;
    }

    void setPrice(Double price){
        this.price=price;
    }

    void setDescription(String description){
        this.description=description;
    }

    void setStock(int Stock){
      this.Stock=Stock;
    }
    @Override
    public String toString() {
        return "[" + itemId + "] " + title + " - Rs. " + price + "\n  Description: " + description +
               "\n  Maker: " + makerName;
    }
    /*String itemId; -
    private String makerId;- 
    private String title;-
    private String description;-
    private String category;-
    private double price;-
    private String makerName;
    private int Stock;*/
   
    public String itemInfo(){
        return itemId + "," + title + "," + price + "," +makerId+","+makerName+","+ category+","+description+","+Stock;
    }
    public void addReview(Review review) {
        reviews.add(review);
        FilehandlingforReviews.writeFile(review);
    }
    public void showReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews for this item yet.");
        } else {
            System.out.println("Reviews:");
            for (Review r : reviews) {
                System.out.println(r);
            }
        }
    }
    public List<Review> getReviews() {
        return reviews;
    }
    
    
}
class Review {
    private String reviewId;
    private String itemId;
    private String reviewerName;
    private String reviewerId;
    private int rating;
    private String comment;

    public Review(String reviewId,String reviewerName, String itemID, int rating, String comment,String reviewerId) {
        this.reviewId = reviewId;
        this.itemId = itemID;
        this.rating = rating;
        this.comment = comment;
        this.reviewerName=reviewerName;
        this.reviewerId=reviewerId;
    }

    public String toString() {
        return "Review by " + reviewerName + " | Rating: " + rating + "/5\nComment: " + comment;
    }
    /*writer.write(ReviewList.get(j).getReviewId()+","
                +ReviewList.get(j).getReviewerName()+","
                +ReviewList.get(j).getItemId()+","
                +ReviewList.get(j).getRating()+","
                +ReviewList.get(j).getComment()+","
                +ReviewList.get(j).getReviewerId()+"\n"); */
    String getReviewId(){
        return reviewId;
    }
    String getReviewerName(){
        return reviewerName;    
    }
    String getItemId(){
        return itemId;
    }
    int getRating(){
        return rating;
    }
    String getComment(){
        return comment;
    }
    String getReviewerId(){
        return reviewerId;
    }
}
class Buyer extends User {
    private Cart cart = new Cart();
    private WishList wishlist = new WishList();
    private List <Order> orders = new ArrayList<>();
    private List <Maker> allMakers;
    private List <Item> allItems;
    private ArrayList <Buyer> buyerlist;

    public Buyer(String userId, String name, String email, String password, String bankpassword) {
        super(userId, name, email, password,bankpassword);
    }

    public void viewAccountBalance() {
        System.out.println("Your current balance is: Rs. " + bankAccount.getBalance());
    }

    public void browseProducts() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Browse Products ---");
            System.out.println("1. Browse by Maker");
            System.out.println("2. Browse by Category");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\nAvailable Makers:");
                for (int i = 0; i < allMakers.size(); i++) {
                    System.out.println((i + 1) + ". " + allMakers.get(i).getName());
                }

                System.out.print("Choose a maker (or 0 to cancel): ");
                int mIndex = scanner.nextInt();
                scanner.nextLine();

                if (mIndex > 0 && mIndex <= allMakers.size()) {
                    Maker selected = allMakers.get(mIndex - 1);
                    List<Item> makerItems = selected.getItems();

                    for (int i = 0; i < makerItems.size(); i++) {
                        Item item = makerItems.get(i);
                        System.out.println((i + 1) + ". " + item.getTitle() + " - Rs. " + item.getPrice());
                    }

                    System.out.print("Select an item (or press 0 to go back): ");
                    int iIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (iIndex > 0 && iIndex <= makerItems.size()) {
                        Item item = makerItems.get(iIndex - 1);
                        System.out.println("1. Save to WishList \n2. Add to Cart\n3. Buy Now\n4. Cancel");
                        int action = scanner.nextInt();
                        scanner.nextLine();

                        int itemindex = 0;
                        int x = 0;
                        for(Item targetitem: this.allItems){
                            if(targetitem.getItemId().equals(targetitem.getItemId())){
                                itemindex = x;
                                break;
                            }
                            x++;
                        }
                        if (action == 1) wishlist.addItem(item,this);
                        else if (action == 2) cart.addItem(item,this);
                        else if (action == 3){
                            cart.removeItem(itemindex, this, buyerlist);
                            placeOrder(item);
                        }
                    }
                }
            } else if (choice == 2) {
                ArrayList<Item> ceramics = new ArrayList<>();
                ArrayList<Item> utensils = new ArrayList<>();
                ArrayList<Item> wood = new ArrayList<>();
                ArrayList<Item> marble = new ArrayList<>();
                ArrayList<Item> pottery = new ArrayList<>();
                ArrayList<Item> ajrak = new ArrayList<>();
                ArrayList<Item> khussa = new ArrayList<>();
                ArrayList<Item> truck = new ArrayList<>();
                ArrayList<Item> pashminashawls = new ArrayList<>();
                ArrayList<Item> basketry = new ArrayList<>();
                ArrayList<Item> embroideredshawls = new ArrayList<>();

                int i;
                //sorting all categorised products in arraylists
                for(Item item:allItems){
                    if(item.getCategory().equals("Ceramics"))
                        ceramics.add(item);
                    else if(item.getCategory().equals("Brass and Copper Utensils"))
                        utensils.add(item);
                    else if(item.getCategory().equals("Wood Carving"))
                        wood.add(item);
                    else if(item.getCategory().equals("Marble Carving"))
                        marble.add(item);
                    else if(item.getCategory().equals("Blue Pottery"))
                        pottery.add(item);
                    else if(item.getCategory().equals("Ajrak"))
                        ajrak.add(item);
                    else if(item.getCategory().equals("Khussa"))
                        khussa.add(item);
                    else if(item.getCategory().equals("Truck Art"))
                        truck.add(item);
                    else if(item.getCategory().equals("Pashmina Shawls"))
                        pashminashawls.add(item);
                    else if(item.getCategory().equals("Basketry"))
                        basketry.add(item);
                    else if(item.getCategory().equals("Embroidered Shawls"))
                        embroideredshawls.add(item);
                }
                
                System.out.println("Available Products Sorted by Category");
                System.out.println("CERAMICS------------");
                i = 1;
                if(ceramics.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:ceramics){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("BRASS AND COPPER UTENSILS------------");
                i = 1;
                if(utensils.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:utensils){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("WOOD CARVING------------");
                i = 1;
                if(wood.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:wood){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("MARBLE CARVING------------");
                i = 1;
                if(marble.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:marble){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("BLUE POTTERY------------");
                i = 1;
                if(pottery.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:pottery){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("AJRAK------------");
                i = 1;
                if(ajrak.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:ajrak){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("KHUSSA------------");
                i = 1;
                if(khussa.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:khussa){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("TRUCK ART------------");
                i = 1;
                if(truck.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:truck){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("PASHMINA SHAWLS------------");
                i = 1;
                if(pashminashawls.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:pashminashawls){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("BASKETRY------------");
                i = 1;
                if(basketry.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:basketry){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                System.out.println("EMBRIODERED SHAWLS------------");
                i = 1;
                if(embroideredshawls.isEmpty())
                    System.out.println("No items stored in this category");
                else{
                    for(Item item:embroideredshawls){
                        System.out.println(i+") "+item);
                        i++;
                    }
                }
                Item item = null;
                int iIndex;
                System.out.println("Select a category you want to buy from [press 0 to exit]");
                System.out.print("\n1) Ceramics   2) Brass and Copper Utensils  3) Wood Carving "
                                +"\n4) Marble Carving  5) Blue Pottery 6) Ajrak 7) Khussa "
                                 + "\n8) Truck Art 9) Pashmina Shawls 10) Basketry 11) Embroidered Shawls");
                do{
                    System.out.print("\nEnter your category:");
                    int index=scanner.nextInt();
                    if(index == 0)
                        break;

                    switch(index){
                        case 1:{
                            if(ceramics.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            }
                            else{
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= ceramics.size())
                                    item = ceramics.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        }break;
                        case 2: {
                            if(utensils.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= utensils.size())
                                    item = utensils.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 3: {
                            if(wood.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= wood.size())
                                    item = wood.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 4: {
                            if(marble.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= marble.size())
                                    item = marble.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 5: {
                            if(pottery.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= pottery.size())
                                    item = pottery.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 6: {
                            if(ajrak.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= ajrak.size())
                                    item = ajrak.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 7: {
                            if(khussa.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= khussa.size())
                                    item = khussa.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 8: {
                            if(truck.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= truck.size())
                                    item = truck.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 9: {
                            if(pashminashawls.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= pashminashawls.size())
                                    item = pashminashawls.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 10: {
                            if(basketry.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= basketry.size())
                                    item = basketry.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        case 11: {
                            if(embroideredshawls.isEmpty()){
                                System.out.print("No Items stored in this category");
                                break;
                            } else {
                                System.out.print("Select an item: ");
                                iIndex = scanner.nextInt();
                                scanner.nextLine();

                                if (iIndex > 0 && iIndex <= embroideredshawls.size())
                                    item = embroideredshawls.get(iIndex - 1);
                                else
                                    System.out.println("Enter a valid item number");
                            }
                        } break;
                        default:{
                            System.out.println("Enter a valid category number");
                        }break;
                    }
                }while(item == null);
                
                if(item == null)
                    break;
                System.out.println("\nSelect an action:");
                System.out.println("1. Save to WishList \n2. Add to Cart\n3. Buy Now\n4. Cancel");
                System.out.print("\nYour choice:");
                int action = scanner.nextInt();
                scanner.nextLine();

                int itemindex = 0;
                int x = 0;
                for(Item targetitem: this.allItems){
                    if(targetitem.getItemId().equals(item.getItemId())){
                        itemindex = x;
                        break;
                    }
                    x++;
                }
                
                if (action == 1) wishlist.addItem(item,this);
                else if (action == 2) cart.addItem(item,this);
                else if (action == 3) {
                    cart.removeItem(itemindex, this, buyerlist);
                    placeOrder(item);
                }
            }
        } while (choice != 3);
////        scanner.close();
    }
        
    @Override
    public void showMenu() {
            Scanner scanner = new Scanner(System.in);
            int choice;
        
            do {
                System.out.println("\n--- Buyer Menu ---");
                System.out.println("1. Browse Products");
                System.out.println("2. Manage Bank Account");
                System.out.println("3. View WishList");
                System.out.println("4. View Cart");
                System.out.println("5. Leave a Review");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
        
                switch (choice) {
                    case 1:
                        browseProducts();
                        break;
                    case 2:{
                        
                        Scanner input = new Scanner(System.in);
                        boolean passwordCorrect=false;
                        String password;
                        
                        do{
                            System.out.print("\nEnter your Bank Account Password:");
                            try{
                                password = input.nextLine();

                                if(!password.equals(this.bankAccount.getAccountPassword()))
                                    throw new WrongInput("Invalid Bank Account Password");
                                else
                                    passwordCorrect = true;
                            }
                            catch(WrongInput e){
                                System.out.println(e);
                            }
                        }while(!passwordCorrect);

                        boolean exitMenu = false;
                        boolean isoptioncorrect = false;
                        int option=0;
                        boolean isAltered = false;
                        double money=0;
                        do{
                            System.out.println("Choose an option: 1) Deposit Balance  2) Withdraw Balance 3) View Balance 4) Exit to Menu");
                            do{
                                try{
                                    System.out.print("\nYour choice: ");
                                    option = input.nextInt();
                                    input.nextLine();
                                    if(option<1 || option>3)
                                        throw new WrongInput("Option must be between 1 and 4");
                                    else
                                        isoptioncorrect = true;
                                }
                                catch(WrongInput e){
                                    System.out.print(e);
                                }                            
                            }while(!isoptioncorrect);

                            switch(option){
                                case 1:{ //deposit
                                    System.out.print("Enter amount to Deposit: PKR");
                                    money = input.nextDouble();
                                    input.nextLine();
                                    this.bankAccount.deposit(money);
                                    isAltered = true;
                                }break;
                                case 2:{ //withdraw
                                    System.out.print("Enter amount to Withdraw: PKR ");
                                    money = input.nextDouble();
                                    input.nextLine();
                                    this.bankAccount.withdraw(money);
                                    isAltered = true;
                                }break;
                                case 3:{ //view balance
                                    viewAccountBalance();
                                }break;
                                case 4:{ //exit to menu
                                   exitMenu = true; 
                                }break;
                            }
                        }
                        while(!exitMenu);
                        if (isAltered){ // rewrite file if theres any alteration in money
                            FileHandlingforBuyer.rewriteFile(this.buyerlist);
                        }
                    }break;
                    case 3:
                        viewWishlist();
                    break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        leaveReview();
                        break;
                    case 6:
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } while (choice != 6);
////            scanner.close();
        }
        
    
    public BankAccount getBankAccount() {
        return bankAccount;
    }
    public void placeOrder(Item item) {
        double price = item.getPrice();
        Scanner scanner=new Scanner(System.in);
    
        // Check if buyer has enough balance
        if (bankAccount.getBalance() < price) {
            System.out.println("Insufficient balance to place order.");
//            scanner.close();
            return;
        }
    
        // Transfer money: Buyer → Maker
        System.out.println("Please enter your bank password before proceeding");
        String password = scanner.nextLine();
        while(!password.equals(bankPassword)){
            System.out.println("Incorrect password, please enter again");
            password = scanner.nextLine();
        }
        Maker m1=null;
        for(int i=0;i<allMakers.size();i++){
           if(allMakers.get(i).getUserId().equals(item.getMakerID())){
                m1=allMakers.get(i);
                break;
           }
        }
        if(m1 == null){
            System.out.println("Maker with ID:"+ item.getMakerID() + " not found in record. Cancelling transaction...");
            return;
        }
        else
            m1.getBankAccount().deposit(price);
    
        bankAccount.withdraw(price);
        // Create Order
        String orderId = "O" + (orders.size() + 1);
        Order order = new Order(orderId, this, item);
        orders.add(order);
        FileHandlingForOrders.writeFile(order);
    
        // Show Receipt
        System.out.println(order.generateReceipt());
//        scanner.close();
    }
    
    public void viewCart() {
        Scanner scanner = new Scanner(System.in);
        int choice;
    
        do {
    
            if (cart.isEmpty()){
                System.out.println(" No items added to cart yet");
                break;
            }
            cart.showCartItems();
    
            System.out.println("\nChoose an item to:");
            System.out.println("1. Buy Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
    
            if (choice == 1 || choice == 2) {
                System.out.print("Enter item number: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
    
                if (index >= 0 && index < cart.getItems().size()) {
                    Item item = cart.getItems().get(index);
    
                    if (choice == 1) {
                        placeOrder(item);
                        cart.removeItem(index,this,this.buyerlist);
                        //cart.getItems().remove(item); done in the above function
                    } else {
                        cart.removeItem(index,this,this.buyerlist);
                    }
                } else {
                    System.out.println("Invalid item number.");
                }
            }
    
        } while (choice != 3);
//        scanner.close();
    }
    public void viewWishlist() {
        Scanner scanner = new Scanner(System.in);
        int choice;
    
        do {
            if (wishlist.isEmpty()){
                System.out.println(" No items saved in wishlist yet");
                break;
            }
            wishlist.showWishListitems();
    
            System.out.println("\nChoose an item to:");
            System.out.println("1. Add to Cart");
            System.out.println("2. Remove from WishList");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
    
            if (choice == 1 || choice == 2) {
                System.out.print("Enter item number: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
    
                if (index >= 0 && index < wishlist.getItems().size()) {
                    Item item = wishlist.getItems().get(index);
    
                    if (choice == 1) {
                        cart.addItem(item, this);
                        wishlist.removeItem(index,this, this.buyerlist);
                    } else {
                        wishlist.removeItem(index, this, this.buyerlist);
                    }
                } else {
                    System.out.println("Invalid item number.");
                }
            }
    
        } while (choice != 3);
//        scanner.close();
    }
    public void leaveReview() {
        Scanner scanner = new Scanner(System.in);
    
        if (orders.isEmpty()) {
            System.out.println("You haven't placed any orders yet.");
//            scanner.close();
            return;
        }
    
        System.out.println("\n--- Your Orders ---");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". " + order.getItem().getTitle() +
                    " - Rs. " + order.getAmount() +
                    " (Maker: " + order.getItem().getMakerName() + ")");
        }
    
        System.out.print("Choose an order to review (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        if (choice > 0 && choice <= orders.size()) {
            Order order = orders.get(choice - 1);
            Item item = order.getItem();
    
            System.out.print("Enter rating (1 to 5): ");
            int rating = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Enter your comment: ");
            String comment = scanner.nextLine();
    
            String reviewId = "R" + (item.getReviews().size() + 1);
            Review review = new Review(reviewId, this.name,item.getItemId(), rating, comment,this.getUserId());
            item.addReview(review);
    
            System.out.println("Thank you! Your review has been submitted.");
        }
//        scanner.close();
    }
    public void setMakerList(List<Maker> makers) {
        this.allMakers = makers;
    }
    public void setItemList(List<Item> items){
        this.allItems = items;
    }
    public void setBuyerList(ArrayList<Buyer> buyerlist){
        this.buyerlist = buyerlist;
    }
    public void addItemfromFile(Item i){
        this.wishlist.items.add(i);
    }
    public String generateItemList(){
        return this.wishlist.generateList(this);
    }
}

class Order {
    private String orderId;
    private Buyer buyer;
    private Item item;
    private double amount;
    private LocalDateTime orderDate;

    public Order(String orderId, Buyer buyer, Item item) {
        this.orderId = orderId;
        this.buyer = buyer;
        this.item = item;
        this.amount = item.getPrice();
        this.orderDate = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Item getItem() {
        return item;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // Generates a simple receipt
    public String generateReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = orderDate.format(formatter);

        return "------------- RECEIPT -------------\n" +
               "Order ID: " + orderId + "\n" +
               "Buyer: " + buyer.getName() + "\n" +
               "Item: " + item.getTitle() + "\n" +
               "Seller: " + item.getMakerName() + "\n" +
               "Price: Rs. " + amount + "\n" +
               "Date: " + formattedDate + "\n" +
               "-----------------------------------";
    }
}
class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public String generateList(Buyer b){
        String list = "";
        if(items.isEmpty())
            return list;
        for(Item i:items){
            list += b.getUserId()+","+b.getName()+","+b.getEmail()+","+i.itemInfo()+"\n";
        }
        return list;
    }
    // Add item to cart
    public void addItem(Item item, Buyer b) {
        items.add(item);
        System.out.println("Item added to cart.");
        String cart = this.generateList(b);
        FileHandlingforCart.writeFile(cart);
    }

    // Remove item by index
    public void removeItem(int index, Buyer b,  ArrayList<Buyer> buyerlist) {
        if (index >= 0 && index < items.size()) {
            Item removed = items.remove(index);
            System.out.println("Removed: " + removed.getTitle());
        } else {
            System.out.println("Invalid item number.");
        }
        
        String wishlist = this.generateList(b);
        FileHandlingforCart.rewriteFile(buyerlist);
    }

    // Get all items
    public List<Item> getItems() {
        return items;
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Show all cart items
    public void showCartItems() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\n--- Your Cart ---");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println((i + 1) + ". " + item.getTitle() + " - Rs. " + item.getPrice());
            }
        }
    }
}

class WishList {
    public List<Item> items;

    public WishList() {
        this.items = new ArrayList<>();
    }
    public String generateList(Buyer b){
        String list = "";
        if(items.isEmpty())
            return list;
        for(Item i:items){
            list += b.getUserId()+","+b.getName()+","+b.getEmail()+","+i.itemInfo()+"\n";
        }
        return list;
    }

    // Add item to wishlist and write to file too
    public void addItem(Item item, Buyer b) {
        items.add(item);
        System.out.println("Item added to Wishlist.");
        String wishlist = this.generateList(b);
        FileHandlingforWishList.writeFile(wishlist);
    }
    // Remove item by index
    public void removeItem(int index, Buyer b, ArrayList<Buyer> buyerlist) {
        if (index >= 0 && index < items.size()) {
            Item removed = items.remove(index);
            System.out.println("Removed: " + removed.getTitle());
        } else {
            System.out.println("Invalid item number.");
        }
        String wishlist = this.generateList(b);
        FileHandlingforWishList.rewriteFile(buyerlist);
    }
    public void addItemfromFile(Item i){
        this.items.add(i);
    }
    // Get all items
    public List<Item> getItems() {
        return items;
    }
    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
    // Show all wishlist items
    public void showWishListitems(){
        if (items.isEmpty()){
            System.out.println("Your WishList is empty.");
        } else {
            System.out.println("\n--- Your Wishlist ---");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println((i + 1) + ". " + item.getTitle() + " - Rs. " + item.getPrice());
            }
        }
    }
}
