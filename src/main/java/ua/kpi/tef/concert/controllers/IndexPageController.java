package ua.kpi.tef.concert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.concert.domain.Artist;
import ua.kpi.tef.concert.repos.ArtistRepo;

@Controller
public class IndexPageController {
    @Autowired
    private ArtistRepo artistRepo;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Artist> artists;

        if(filter != null && !filter.isEmpty()){
            artists = artistRepo.findByType(filter);
        }
        else {
            artists = artistRepo.findAll();
        }

        model.addAttribute("artists", artists);
        model.addAttribute("filter", filter);
        return "main";
    }
}