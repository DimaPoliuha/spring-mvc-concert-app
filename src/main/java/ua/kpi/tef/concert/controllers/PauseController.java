package ua.kpi.tef.concert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.concert.entities.Pause;
import ua.kpi.tef.concert.repos.PauseRepo;

@Controller
@RequestMapping("/pause")
public class PauseController {
    @Autowired
    private PauseRepo pauseRepo;

    @PostMapping("/add-pause")
    public String addPause(@RequestParam String type, @RequestParam int duration){
        Pause pause = new Pause(type, duration);
        pauseRepo.save(pause);

        return "redirect:/";
    }

    @GetMapping("pause-edit/")
    public String editPause(@RequestParam int id, Model model){
        Pause pause = pauseRepo.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("type", pause.getType());
        model.addAttribute("duration", pause.getDuration());
        return "pauseEdit";
    }

    @GetMapping("pause-delete/")
    public String deleteArtist(@RequestParam int id){
        pauseRepo.delete(pauseRepo.findById(id));
        return "redirect:/";
    }

    @PostMapping("pause-update/")
    public String updateArtist(@RequestParam int id, @RequestParam String type, @RequestParam int duration){
        Pause pause = pauseRepo.findById(id);
        pause.setType(type);
        pause.setDuration(duration);
        pauseRepo.save(pause);
        return "redirect:/";
    }
}
