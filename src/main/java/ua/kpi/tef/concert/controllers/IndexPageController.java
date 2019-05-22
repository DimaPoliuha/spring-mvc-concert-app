package ua.kpi.tef.concert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.concert.entities.Artist;
import ua.kpi.tef.concert.entities.Pause;
import ua.kpi.tef.concert.repos.ArtistRepo;
import ua.kpi.tef.concert.repos.PauseRepo;

@Controller
public class IndexPageController {
    @Autowired
    private ArtistRepo artistRepo;
    @Autowired
    private PauseRepo pauseRepo;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Artist> artists;
        Iterable<Pause> pauses;

        if(filter != null && !filter.isEmpty()){
            artists = artistRepo.findByType(filter);
            pauses = null;
        }
        else {
            artists = artistRepo.findAll();
            pauses = pauseRepo.findAll();
        }

        model.addAttribute("artists", artists);
        model.addAttribute("filter", filter);
        model.addAttribute("pauses", pauses);
        return "main";
    }

    @PostMapping("/reset-filter/")
    public String resetFilter(){
        return "redirect:/";
    }
}