package ua.kpi.tef.concert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.concert.entities.Artist;
import ua.kpi.tef.concert.repos.ArtistRepo;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepo artistRepo;

    @PostMapping
    public String addArtist(@RequestParam String name, @RequestParam String type){
        Artist artist = new Artist(name, type);

        artistRepo.save(artist);

        return "redirect:/";
    }

    @GetMapping("/artist-edit/")
    public String editArtist(@RequestParam int id, Model model){
        Artist artist = artistRepo.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("name", artist.getName());
        model.addAttribute("type", artist.getType());
        return "messageEdit";
    }

    @GetMapping("/artist-delete/")
    public String deleteArtist(@RequestParam int id){
        artistRepo.delete(artistRepo.findById(id));
        return "redirect:/";
    }

    @PostMapping("/artist-edit/")
    public String updateArtist(@RequestParam int id, @RequestParam String name, @RequestParam String type){
        Artist artist = artistRepo.findById(id);
        artist.setName(name);
        artist.setType(type);
        artistRepo.save(artist);
        return "redirect:/";
    }
}
