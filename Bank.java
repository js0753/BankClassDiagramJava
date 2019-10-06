import java.util.*;
import java.lang.*;
class Customer{
String nam;
int card_no;
int pin;
String pass;
static int accounts=0;
Vector<Account> v= new Vector<Account>();
Vector <Transaction> t=new Vector<Transaction>();
static Scanner sc=new Scanner(System.in);
int flagp = 3; 

boolean verifyPassword(String p){
if(p.equals(pass)){
flagp = 3;
System.out.println("Verified User");
return true;
}
else{
  flagp--;
  if(flagp != 0){
    System.out.println("Incorrect Password. " + flagp + " attempt(s) left");
    System.out.println("Enter password:");
    p=sc.next();
    return verifyPassword(p);
  }
  else{
    flagp = 3;
    System.out.println("Verification failed");
    return false;
  }
}
}
}

class Account extends Customer{
int account_no;
float balance;

Account(){
balance=0;
}

int deposit(float x){
balance+=x;
return 1;
}
int withdraw(float y){
if(balance>0)
{
balance-=y;
return 1;
}
else
{
System.out.println("Insufficient Balance");
return 0;
}
}
}
 
class Transaction{
static Scanner s=new Scanner(System.in);

int transaction_id;
String type;
float amount;
float balance;

Transaction(int x){
transaction_id=x;
}

void modifies(Account a){
int f,flag=0;
System.out.println("Choose Transaction:\n1.Deposit\n2.Withdraw");
f=s.nextInt();
System.out.println("Enter amount");
amount=s.nextFloat();

switch(f){
case 1:
flag=a.deposit(amount);
type="deposit";

break;
case 2:
flag=a.withdraw(amount);
type="withdrawal";
break;
}
if(flag==1)
System.out.println("\nSuccessful "+type+" of amount "+amount+"\nBalance of Account number "+a.account_no+" is "+a.balance);
balance=a.balance;
}
}

class CurrentAccount extends Account{

CurrentAccount(){
super();
}
int withdraw(float y){
super.balance-=y;
return 1;
}
}

class SavingsAccount extends Account{

SavingsAccount(){
  super();
}
}

class Main
{
static Scanner sc=new Scanner(System.in);

static void add_account(Customer x){
int g;
Account a;

x.accounts++;

System.out.println("Choose Account type:\n1.Current\n2.Savings");
g=sc.nextInt();
switch(g){
case 1:
a=new CurrentAccount();
a.account_no=x.accounts;
(x.v).add(a);
break;
case 2:
a=new SavingsAccount();
a.account_no=x.accounts;
(x.v).add(a);
break;
}
}

static void delete_account(Customer x){
System.out.println("\nEnter Account number to be Deleted");
Vector<Account> v=x.v;
int n=sc.nextInt();

for(int i=0;i<v.size();i++){
if(v.get(i).account_no==n){
v.removeElementAt(i);
break;
}
}
}

static void modify_account(Customer a){
System.out.println("Choose detail to modify:\n1.Name\n2.Card Number\n3.PIN\n4.Password");
int f=sc.nextInt();
switch(f){
case 1:
System.out.println("Enter new name: ");
a.nam=sc.next();
break;
case 2:
System.out.println("Enter new Card Number: ");
a.card_no=sc.nextInt();
break;
case 3:
System.out.println("Enter new Pin: ");
a.pin=sc.nextInt();
break;
case 4:
System.out.println("Enter new password: ");
a.pass=sc.next();
break;
}
}

static void display(Customer x){
System.out.println("\nName : "+x.nam);
for(int i=0;i<(x.v).size();i++){

System.out.println("Account No: "+(x.v).get(i).account_no+"\nBalance:"+(x.v).get(i).balance);
}
}

public static void main(String args[])
{
Customer p1=new Customer();

int d,g,no;
int tid=0;
System.out.println("Enter your details");
System.out.print("\nName: ");
p1.nam=sc.next();

System.out.print("\nCard Number: ");
p1.card_no=sc.nextInt();
System.out.print("\nPin ");
p1.pin=sc.nextInt();

System.out.println("\nEnter Password: ");
p1.pass=sc.next();
System.out.println("\nHello "+p1.nam);

do{
System.out.println("Select Option\n1.Add Account\n2.Delete Account\n3.Modify Account Details\n4.Print Balance\n5.Perform a Transaction");
g=sc.nextInt();

switch(g){
case 1:
add_account(p1);
break;

case 2:
delete_account(p1);
break;

case 3:
modify_account(p1);
break;

case 4:
display(p1);
break;

case 5:
String p;
System.out.println("Enter password:");
p=sc.next();
if(p1.verifyPassword(p)){
Transaction l=new Transaction(tid++);
int i;
System.out.println("Enter Account no to modify");
no=sc.nextInt();
for(i=0;i<(p1.v).size();i++){
if(((p1.v).get(i)).account_no==no)
break;
}
l.modifies((p1.v).get(i));
(p1.t).add(l);

break;
}
}
System.out.println("1.Continue\n0.End");
d=sc.nextInt();
}while(d==1);
}
}
