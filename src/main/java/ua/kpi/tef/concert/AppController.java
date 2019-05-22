package ua.kpi.tef.concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.concert.domain.Artist;
import ua.kpi.tef.concert.repos.ArtistRepo;

import java.util.Map;

@Controller
public class AppController {
    @Autowired
    private ArtistRepo artistRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

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

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String type){
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
}