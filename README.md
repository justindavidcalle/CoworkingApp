# CoworkingApp

## Einführung

Die CoworkingApp ist eine Webanwendung zur Verwaltung von Mitgliedschaften und Buchungen für einen Coworking Space in Zürich. Die Anwendung umfasst ein Administrationspanel, die Registrierung von Mitgliedern, die Verwaltung von Buchungen und verschiedene weitere Funktionen, um den Betrieb eines Coworking Spaces effizient zu gestalten.

## Erste Schritte

Diese Anleitung hilft Ihnen, das Projekt auf Ihrem lokalen Rechner für Entwicklungs- und Testzwecke einzurichten.

### Voraussetzungen

- Java 17 oder höher
- Maven 3.6.3 oder höher
- MySQL 8.0 oder höher

### Installation

1. **Repository klonen**:
    ```bash
    git clone https://github.com/yourusername/coworkingapp.git
    cd coworkingapp
    ```

2. **MySQL-Datenbank einrichten**:
    ```sql
    CREATE DATABASE coworkingapp;
    ```

3. **Datenbank-Anmeldedaten konfigurieren**:
   Bearbeiten Sie die Datei `src/main/resources/application.properties`, um Ihre MySQL-Datenbank-Anmeldedaten festzulegen:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/coworkingapp
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.initialization-mode=always
    spring.datasource.schema=classpath:schema.sql
    spring.datasource.data=classpath:data.sql
    ```

4. **Projekt bauen**:
    ```bash
    mvn clean install
    ```

5. **Anwendung starten**:
    ```bash
    mvn spring-boot:run
    ```

### Testdaten

Die Anwendung lädt beim Start automatisch Testdaten. Dies ermöglicht konsistente Tests und die Validierung der Funktionen.

#### Testdaten-Dateien

Die Testdaten sind in den SQL-Dateien im Verzeichnis `src/main/resources` definiert:

- **schema.sql**: Enthält SQL-Befehle zum Erstellen des Datenbankschemas.
- **data.sql**: Enthält initiale Testdaten für Benutzer und Buchungen.

#### Laden der Testdaten

Um sicherzustellen, dass die Testdaten geladen werden:

1. **Stellen Sie sicher, dass die Anwendung gestartet ist**:
    - Die notwendigen Konfigurationen befinden sich in der Datei `application.properties`:
      ```properties
      spring.datasource.initialization-mode=always
      spring.datasource.schema=classpath:schema.sql
      spring.datasource.data=classpath:data.sql
      ```

2. **Definieren Sie Testdaten**:
    - Testdaten für Benutzer und Buchungen sind in der Datei `data.sql` definiert.
    - Beispiel-Einträge:
      ```sql
      -- Einfügen von Testdaten für Benutzer
      INSERT INTO user (id, first_name, last_name, email, password, role) VALUES
      (1, 'Admin', 'User', 'admin@coworkingapp.com', 'adminpass', 'ADMIN'),
      (2, 'John', 'Doe', 'john.doe@example.com', 'password', 'MEMBER'),
      (3, 'Jane', 'Doe', 'jane.doe@example.com', 'password', 'MEMBER');
 
      -- Einfügen von Testdaten für Buchungen
      INSERT INTO booking (id, user_id, date, half_day, status) VALUES
      (1, 2, '2024-07-15', true, 'PENDING'),
      (2, 2, '2024-07-16', false, 'CONFIRMED'),
      (3, 3, '2024-07-17', true, 'CANCELLED'),
      (4, 3, '2024-07-18', false, 'PENDING');
      ```

3. **Anwendungsstart**:
    - Beim Start der Anwendung werden die Testdaten automatisch geladen und stehen zur Verfügung.

### Hinweis

Stellen Sie sicher, dass Ihre MySQL-Datenbank läuft und mit den in der Datei `application.properties` angegebenen Anmeldedaten zugänglich ist. Passen Sie die Datenbank-URL, den Benutzernamen und das Passwort bei Bedarf an.

## API-Endpunkte

### Registrierung und Authentifizierung
- **POST /users**: Registrierung neuer Benutzer
- **POST /login**: Benutzeranmeldung und JWT-Erhalt

### Verwaltung der eigenen Buchungen als Mitglied
- **GET /bookings/{id}**: Abrufen einer spezifischen Buchung
- **POST /bookings**: Erstellen einer neuen Buchung
- **DELETE /bookings/{id}**: Stornieren einer Buchung

### Verwaltung der Buchungen durch Administratoren
- **PUT /bookings/{id}**: Bearbeiten bestehender Buchungen
- **POST /bookings/status**: Akzeptieren oder Ablehnen von Buchungen

### Verwaltung von Mitgliedern durch Administratoren
- **GET /users**: Abrufen aller Benutzer
- **GET /users/{id}**: Abrufen eines spezifischen Benutzers
- **DELETE /users/{id}**: Löschen eines Benutzers
- **PUT /users/{id}**: Bearbeiten eines Benutzers

### Zusätzliche Anforderungen
- **GET /users**: Abrufen aller Benutzerinformationen
- **POST /login**: Authentifizierung und Token-Generierung

## Code-Struktur

Das Projekt folgt einer Schichtenarchitektur, um die Trennung der Verantwortlichkeiten und die Wartbarkeit zu gewährleisten.

- **Controller-Schicht**: Behandelt HTTP-Anfragen und -Antworten.
- **Service-Schicht**: Enthält Geschäftslogik.
- **Repository-Schicht**: Interagiert mit der Datenbank.

## Versionsgeschichte

Die vollständige Versionsgeschichte wird im Git-Repository gepflegt. Sie können den Commit-Verlauf einsehen, um detaillierte Änderungen im Laufe der Zeit nachzuvollziehen.

## Sicherheit

- **JWT-Authentifizierung**: Die Anwendung verwendet JSON Web Tokens (JWT) zur Authentifizierung. Die Tokens laufen 24 Stunden nach der Ausstellung ab.
- **Verschlüsselung**: Sensible Daten wie Passwörter werden mit BCrypt verschlüsselt.

## Skalierbarkeit

Die Anwendung ist so konzipiert, dass sie wachsende Benutzerzahlen und Buchungen effizient bewältigen kann. Die Architektur ermöglicht eine einfache Skalierung und Leistungsoptimierung.

## Beitrag

Beiträge zur Verbesserung der CoworkingApp sind willkommen. Bitte forken Sie das Repository und senden Sie Pull-Requests.
