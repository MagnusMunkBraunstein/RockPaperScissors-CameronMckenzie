package org.example.kenzy.RestController;

import org.example.kenzy.Model.Score;
import org.springframework.web.bind.annotation.*;

@RestController

// Cross origin allows a rest api to be invoked(called) by ressources that are not hosted on the same domain
@CrossOrigin
public class ScoreRestController {

    //det objekt vi ændre på ved kald af de forskelige mappings
    static Score score = new Score(30, 20, 10);


    // get health check
    //   health-check is an endpoint
    @GetMapping("/health-check")
    //Normal Java method
    public String getHealthCheck() {
        return "Situration Normal All Fired Up!";
    }

    // the score comes as an json object
    @GetMapping("/score")
    public Score getScore() {
        return score;
    }


//------------------------------------------------------------------------------------------------------------------------

    // Dennne get mapping er en samling af de tre udkommenterede getmappings

    @GetMapping("/score/{winslossesorties}")
    public int getScoreWinsLossesOrTies(@PathVariable String winslossesorties) {

        if (winslossesorties.equalsIgnoreCase("wins")) {
            return score.getWins();}

        if (winslossesorties.equalsIgnoreCase("ties")) {
            return score.getTies();}

        // i denne guide er dettte en fin ide men man kan til losses ved at skrive /score/bubblegum og den viser stadig losses
        return score.getLosses();
    }
    /*
    @GetMapping("/score/wins")
    public int getWins(){
        return score.getWins();
    }

    @GetMapping("/score/losses")
    public int getLosses(){
        return score.getLosses();
    }

    @GetMapping("/score/ties")
    public int getTies(){
        return score.getTies();
    }

     *///----------------------------------------------------------------------------------------------------------------------------------


    // add a win to the score
    @PostMapping("/score/wins")
public Score addWin(){
    score.setWins(score.getWins() + 1);
    return score;
}

@PostMapping("/score/losses")
    public Score addLoss() {
    score.setLosses(score.getLosses() + 1);
    return score;
}

@PostMapping("score/ties")
    public Score addTies() {
    score.setTies(score.getTies() + 1);
    return score;
}


//------------------------------------------------------------------------------------------------------------------------
    //Changes a specific value as numer of wins or looses
@PatchMapping("/score/wins")
    public Score updateWins(@RequestParam(name="new-value") int newValue) {
        score.setWins(newValue);
        return score;}

    @PatchMapping("/score/losses")
    public Score updateLosses(@RequestParam(name="new-value") int newValue){
        score.setLosses(newValue);
        return score;}

    @PatchMapping("/score/ties")
    public Score updateTies(@RequestParam(name="new-value") int newValue){
        score.setTies(newValue);
        return score;
    }



    //--------------------------------------------------------------------------------------------------------------------
    // put er hvis man vil skifte alle data i objektet
    // Tager en hel body i json format, kan testes i postman
    // feks kan dette sættes in i postman ({
    //    "wins": 99,
    //    "losses": 88,
    //    "ties": 66
    //}
    @PutMapping("/score")
    public Score replaceScore(@RequestBody Score newScore){
        score = newScore;
        return score;}
//------------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/score")
    public void deleteScore(){
        score = null;
    }



}





