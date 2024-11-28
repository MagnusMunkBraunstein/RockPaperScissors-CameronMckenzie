console.log("Jeg er i rockpaperscissors");

// Funktion til at spille sten-saks-papir, hvor systemet altid vælger sten
const playRoshambo = async function(clientGesture) {
    let url = "http://localhost:8080/score/wins"; // Standard-URL for en sejr
    let result = "win"; // Standardresultat antages at være en sejr

    // Konverterer brugerens valg til små bogstaver for at undgå case-sensitivitet
    clientGesture = clientGesture.toLowerCase();

    // Tjekker om brugerens valg var sten
    if (clientGesture === 'rock') {
        result = "tie"; // Hvis det er sten, er resultatet uafgjort
        url = "http://localhost:8080/score/ties"; // URL opdateres til uafgjort
    }
    // Tjekker om brugerens valg var saks
    else if (clientGesture === 'scissors') {
        result = "loss"; // Hvis det er saks, er resultatet et tab
        url = "http://localhost:8080/score/losses"; // URL opdateres til tab
    }

    try {
        // Sender en POST-anmodning til serveren med den valgte URL
        const response = await fetch(url, { method: "POST" });

        // Tjekker om svaret fra serveren er OK (200-statuskode)
        if (!response.ok) throw new Error("Netværkssvaret var ikke OK");

        // Konverterer svaret fra serveren til JSON-format
        const theScore = await response.json();

        // Opdaterer HTML-elementet med id "wins" for at vise antallet af sejre
        document.getElementById("wins").innerHTML = "<b>Sejre:</b> " + theScore.wins;

        // Opdaterer HTML-elementet med id "losses" for at vise antallet af nederlag
        document.getElementById("losses").innerHTML = "<b>Tab:</b> " + theScore.losses;

        // Opdaterer HTML-elementet med id "ties" for at vise antallet af uafgjorte spil
        document.getElementById("ties").innerHTML = "<b>Uafgjorte:</b> " + theScore.ties;

        // Viser det endelige resultat af spillet i HTML-elementet med id "results"
        document.getElementById("results").innerHTML = "Resultatet var: " + result;
    } catch (error) {
        // Logger en fejlmeddelelse i konsollen, hvis der opstår en fejl under fetch
        console.log("Fetch-fejl: " + error.message);
    }
};
