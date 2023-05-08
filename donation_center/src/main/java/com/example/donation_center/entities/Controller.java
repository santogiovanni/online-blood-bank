package com.example.donation_center.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    private List<Schedule> schedules = ApptRandomizer.generateRandomAppts(10);

    /**
     * @return returns a list of all current appointments
     */
    @GetMapping("/blood-appts")
    public List<Schedule> getAllAppointments() {
        return schedules;
    }

    /**
     * @param id ID of a specific donor
     * @return returns information about a specific blood appointment
     */
    @GetMapping("/blood-appts/{Id}")
    public Schedule getAppointment(@PathVariable("Id") int id) {

        return getSchedule(id);
    }

    private Schedule getSchedule(int id) {
        for (Schedule appt : schedules) {

            if (appt.getId() == id) {
                return appt;
            }
        }
        return null;
    }

    /**
     * @return returns list of all appointments for a specific bloodtype
     */
    @GetMapping("/blood-appts/{bloodtype}")
    public List<Blood> getBloodSchedules(@PathVariable("bloodtype") String bloodType) {

        List<Blood> bloodSchedulesByBloodType = new ArrayList<>();

        for (Schedule appt : schedules) {

            if (appt.getBloodType().equals(bloodType)) {

                bloodSchedulesByBloodType.add(new Blood(appt.getBloodType(), appt.getId(), 1));
            }
        }

        return bloodSchedulesByBloodType;
    }

    /**
     * removes specific blood donation via ID
     * 
     * @param id ID of specific blood donor
     */
    @DeleteMapping("/remove-appt/{id}")
    public void deleteAppointment(@PathVariable("id") int id) {

        Schedule schedule = getSchedule(id);
        if (schedule != null) {
            schedules.remove(schedule);
        }
    }

    /**
     * creates a new blood appointment
     * 
     * @param donor donor information parameters
     * @return
     */
    @PostMapping("/create-appt")
    public ResponseEntity<Schedule> createAppointment(@Valid @RequestBody Schedule appt) {

        schedules.add(appt);
        return ResponseEntity.ok(appt);
    }

    /**
     * creates a new blood appointment and pushes blood to blood bank simultaneously
     * 
     * @param donor donor information parameters
     * @return
     */
    @PostMapping("/create-appt-and-push-blood")
    public ResponseEntity<Schedule> createAppointmentAndPushBlood(@Valid @RequestBody Schedule appt) {

        schedules.add(appt);
        pushBlood(new Blood(appt.getBloodType(), appt.getId(), 1));
        return ResponseEntity.ok(appt);
    }

    /**
     * @param id           donor ID
     * @param donorDetails all appointment parameters that would like to be updated
     *                     for existing appointment
     * @return
     */
    @PutMapping("/update-appt/{id}")
    public ResponseEntity<Schedule> updateAppointment(@PathVariable("id") int id, @RequestBody Schedule appt) {

        Schedule updatedAppt = ((Controller) schedules).getAppointment(id)
                .orElseThrow();

        updatedAppt.setName(appt.getName());
        updatedAppt.setBloodType(appt.getBloodType());
        updatedAppt.setTime(appt.getTime());
        updatedAppt.setDate(appt.getDate());

        int apptIndex = getApptIndex(updatedAppt);

        if (apptIndex >= 0) {
            schedules.set(apptIndex, updatedAppt);
        } else {
            throw new IllegalArgumentException("Schedule not found in the list");
        }

        return ResponseEntity.ok(updatedAppt);
    }

    /**
     * 
     * @param schedule
     * @return
     */
    private int getApptIndex(Schedule schedule) {
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).equals(schedule)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @return
     */
    @PostMapping("/pushBlood")
    public ResponseEntity<String> pushBloodType(@RequestParam("bloodtype") String bloodType) {

        List<Blood> bloodSchedulesByBloodType = getBloodSchedules(bloodType);

        if (bloodSchedulesByBloodType == null) {
            return ResponseEntity.badRequest()
                    .body("There is no blood of " + bloodType + " blood type available in this donation center.");
        }

        for (Blood bloodUnit : bloodSchedulesByBloodType) {
            pushBlood(bloodUnit);

        }

        schedules.removeAll(bloodSchedulesByBloodType);

        return ResponseEntity.ok(bloodType + " blood type has been pushed from donation center to blood bank.");
    }

    private Blood pushBlood(Blood blood) {

        String host = System.getProperty("BLOOD_BANK_SERVICE", "localhost");
        String port = System.getProperty("BLOOD_BANK_PORT", "8081");
        String url = "http://" + host + ":" + port + "/addblood";
        RestTemplate restTemplate = new RestTemplate();

        try {

            blood = restTemplate.postForObject(url, blood, Blood.class);
        } catch (final HttpClientErrorException e) {

            System.out.println("Blood of " + blood.getBloodType() + " doesn't exist in blood bank; returning null.");
        }

        return blood;
    }
}