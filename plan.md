#Don't Wreck My House  
**Project Time Estimate: 23:30**  
Time breakdowns on Trello board: 
https://trello.com/b/yKylf9qF/java-mastery-project

##Setup and Models
- **Create packages**
- **Create models** 
  - **Host**
    UUID hostId
    String lastName  
    String email  
    String phone  
    String address  
    String city  
    String state  
    String postalCode  
    BigDecimal standardRate  
    BigDecimal weekendRate
  - **Guest**  
    int guestId
    String firstName  
    String lastName  
    String email
  - **Reservation**  
    int reservationId  
    LocalDate startDate  
    LocalDate endDate  
    int guestId  
    BigDecimal total
- **Create classes**  
  - **Data**
    - ReservationRepository
    - ReservationFileRepository  
      viewReservationByHost()  
      addReservation()  
      editReservation()  
      cancelReservation()  
      getFilePath()  
      serialize()  
      writeAll()
    - HostRepository  
    - HostFileRepository  
      findAll()  
    -GuestRepository  
    - GuestFileRepository  
      findAll()
  - **Domain**
    - ReservationService  
      viewReservationByHost()  
      addReservation()  
      editReservation()  
      cancelReservation()  
      validate()  
      validateNulls()  
      validateFields()
    - Result  
      isSuccess()  
      addErrorMessage()  
      getErrorMessages()
  - **UI**
    - Controller
      run()  
      runMenu()  
      viewReservationByHost()   
      addReservation()  
      editReservation()  
      cancelReservation()  
      getHost()  
      getGuest()  
    - View  
      mainMenu()  -May make separate menu class as in week 4 assessment  
      displayReservations()  
      promptHostEmail()  
      promptGuestEmail()  
      displayHeader()  
      displayMessage()  
- Spring DI

##Data  
###Reservation Repository
- View Reservations
    - Implement
    - Write tests
- Make Reservation
    - Implement
    - Write tests
- Edit Reservation
    - Implement
    - Write tests
- Cancel Reservation
    - Implement
    - Write tests

##Domain  
###Reservation Service
- View Reservations
  - Write tests
- Make Reservation
  - Write tests
- Edit Reservation
  - Write tests
- Cancel Reservation
  - Write tests
- Validation  
###Result

##UI  
####Controller  
- View Reservations
- Make Reservation
- Edit Reservation
- Cancel Reservation  

####View  
- View Reservations
- Make Reservation
- Edit Reservation
- Cancel Reservation

##Bug Fixes and Polish
- Run app and check functionality
    - Note bugs found
- Fix bugs
- Additional polish