# Definsianje REST API endpoint-a i DTO za svaku tacku specifikacije

## Napomene
- mozda napraviti jedan DTO za AccommodationCard koji ce obuhvatati sve zajednicke podatke a da se razlika podataka salje u nezavisnom DTO-u -> povecava se modularnost
- ideja da razdvojimo AccommodationReview i HostReview u 2 kontrolera(trenutno napisano) ili da svi budu u jednom ali da se pristupa preko dodatnog /accommodations ili /hosts (analogno i za report)

### 1. Registracija naloga
##### DTO: 
- UserRegisterDTO -> User(bez status) + Person + Address
- ? odvojeno User + Person i Address
##### REQUEST: 
- api/v1/users 
post
### 2. Prijava na sistem
##### DTO: 
- UserLoginDTO -> email + password
##### REQUEST: 
- api/v1/users 
post
### 3. Upravljanje nalogom
##### DTO: 
- [isto kao UserRegistrationDTO](#1-registracija-naloga)
##### REQUEST: 
- api/v1/users
put 
### 4. Brisanje naloga
##### DTO: 
- id 
##### REQUEST: 
- api/v1/users
delete
### 5. Kreiranje smeštaja
##### DTO: 
- AccommodationDTO -> Accommodation(bez status) + **Address**
- **PriceDTO -> id + start + end + price**
- - moze biti vise ovih requestova ili lista da se prosledi u jednom
##### REQUEST: 
- api/v1/accommodations
post 
- **api/v1/days**
post
### 6. Definisanje dostupnosti, cene smeštaja i roka za rezervaciju
##### DTO: 
- [isto kao prethodni](#5-kreiranje-smeštaja)
##### REQUEST: 
- api/v1/accommodations
put
### 7. Odobravanje smeštaja
##### DTO: 
- AccommodationCardAdminDTO -> Id + name + image + distanceFromCenter + status + isFreeCancellation + Amenities + timeStamp
- **AccommodationStatusDTO -> Id + status**
##### REQUEST: 
- /api/v1/accommodations
get (/forApproval) 
```sql
        select a from Accommodations a where a.status = 'NEW' or a.status = 'UPDATED';
```
get (/forApproval?status=UPDATED)
```sql
        select a from Accommodations a where a.status = :status;
```
put
### 8. Pregled i ažuriranje smeštaja
#### DTO: 
- AccommodationCardHostDTO -> Id + name + image + distanceFromCenter + status + isFreeCancellation + Amenities
- **RatingDTO -> rating + numOfReviews (na frontu zakljuciti da je Excellent)**
- ? mozda spojiti rejting sa podacima na kartici 
#### REQUEST: 
- /api/v1/accommodations
get (/{hostId})
```sql
        select a from Accommodations a where a.host = :hostId;
```
- put 
### 9. Pretraga i filtriranje smeštaja
##### DTO: 
- AccommodationCardGuestDTO -> AccommodationCardHostDTO + totalPrice + duration
##### REQUEST: 
- /api/v1/accommodations
get (/)
komplikovaniji upit od ovoga jer se proverava i dostupnost u kalendaru
```sql
        select a from Accommodations a where a.city = :city and :numOfGuests between(a.minCap, a.maxCap) and :startDate...;
```
### 10. Detalji smeštaja -> TODO
##### DTO: 
- AccommodationInfoDTO: Id + name + shortDescription + description + Amenities + isFreeCancellation + Address
- TODO: Images
- RatingDTO -> rating + numOfReviews (na frontu zakljuciti da je Excellent) (odnosi se na prosecnu ocenu)
- ReviewDTO -> Review + timeStamp(dodati u Review) 
##### REQUEST:
- /api/v1/accommodations
get(/{accommodationId})
- /api/v1/reviews-accommodations
get(/{accommodationId})
- /api/v1/images-accommodations
get(/{accommodationId})
### 11. Zahtevi za rezervaciju smeštaja
##### DTO:
- ReservationDTO: Reservation
- id
##### REQUEST:
- /api/v1/reservations
post 
delete (logicki)
### 12. Definisanje načina potvrde rezervacije
- definisano u [kreiranje smestaja](#5-kreiranje-smeštaja)
### 13. Pregled i filtriranje zahteva
##### DTO:
- ReservationCardDTO -> reservationId + AccommodationCardDTO(neki od) + startDate + endDate + price
##### REQUEST:
- /api/v1/reservations
get(/{hostId})
```sql
        select r, a from Accommodations a fetch join Reservations r where a.hostId = :hostId;
```
get(/{hostId?filter=value...})
### 14. Brisanje zahteva
##### DTO:
- reservationId
##### REQUEST:
- delete
### 15. Potvrda rezervacije
*samo ako je Manuelna potvrda rezervacije ukljucena*
##### DTO:
- ReservationStatusDTO: id + status
##### REQUEST:
- /api/v1/reservations
put
### 16. Pregled i otkazivanje rezervacije
##### DTO:
- [isto kao ReservationCardDTO](#13-pregled-i-filtriranje-zahteva)
- ReservationStatusDTO: id + status
##### REQUEST:
- /api/v1/reservations
get(/{guestId})
get(/{guestId?filter=value...})
put (za otkazivanje)
### 17. Komentarisanje i ocenjivanje vlasnika -> TODO
##### DTO:
- [isto kao ReviewDTO](#10-detalji-smeštaja) (za priakz srednje ocene)
- [isto kao RatingDTO](#8-pregled-i-ažuriranje-smeštaja) (za prikaz svih komentara)
##### REQUEST:
- /api/v1/reviews-hosts
post
delete
get(/{hostId})
### 18. Prijavljivanje komentara i ocene -> TODO
##### DTO:
- ReviewReportDTO -> ReviewReport
##### REQUEST:
- /api/v1/reports-reviews
post
### 19. Komentarisanje i ocenjivanje smeštaja -> TODO
##### DTO:
- ReviewDTO -> Review
##### REQUEST:
- /api/v1/reviews-accommodations
post
put (odobravanje od strane admina)
get(/{accommodationId})
### 20. Odobravanje komentara i ocena -> TODO
##### DTO:
- ReviewApprovalDTO -> id + status
##### REQUEST:
- /api/v1/reviews-accommodations
delete
- /api/v1/reviews-hosts
delete
### 21. Notifikacije -> TODO
? proveriti sa Popom
### 22. Prijavljivanje korisnika
##### DTO:
- UserReportDTO -> UserReport
##### REQUEST:
- /api/v1/reports-users
post
### 23. Blokiranje korisnika -> TODO
##### DTO:
- UserStatusDTO -> id + status
##### REQUEST:
- /api/v1/users
put
### 24. Izveštaji -> TODO
##### DTO:
- ReviewApprovalDTO -> id + status
##### REQUEST:
- /api/v1/reservations
get(/analytics?start=date&end=date)
get(/analytics/{accommodationId})
### 25. Omiljeni smeštaji
##### DTO:
- [isto kao AccommodationCardGuestDTO](#9-pretraga-i-filtriranje-smeštaja) 
##### REQUEST:
- /api/v1/accommodations
get(/favorites/{guestId})
