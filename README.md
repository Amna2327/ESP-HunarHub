# ESP-HunarHub
Extension of class EndSemesterProject, available at https://github.com/Amna2327/HunarHub-in-progress

## Extra Features:

File Handling:
- Added for Orders, BankAccount (Buyer Only), WishList, Cart, Courses, Items
- Objects read from existing file are added in ArrayLists
- Objects are written to file when created
- File is overwritten with updated info when Objects are updated (Buyer BankAccount Balance, Wishlist/Cart Items removed etc)

Buyer Class:
- Added WishList
- BankAccount Logic added i.e. deposit, withdraw and view balance [added to Buyer ONLY for now, can be copy pasted to maker too]
- Added Browsing items based upon category and respective item selection with the necessary actions

Admin Class:
- Added Course and CourseManager class
- manages courses (Add, Remove, Modify Details)

NOT ADDED
[Admin class]
- Remove Flagged Makers for scams
- Notify Flagged Makers for Complaints/Warning
