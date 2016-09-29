package tw.healthcare.andy.services.converters;

import tw.healthcare.andy.entities.Nurse;
import tw.healthcare.andy.entities.Patient;
import tw.healthcare.andy.entities.VisitingSchedule;
import tw.healthcare.andy.entities.VitalRecord;
import tw.healthcare.andy.services.dtos.NurseDto;
import tw.healthcare.andy.services.dtos.PatientDto;
import tw.healthcare.andy.services.dtos.ScheduleDto;
import tw.healthcare.andy.services.dtos.VitalsDto;

public class EntityDtoConverter {
    public static Nurse toNurse(NurseDto dto) {
        Nurse nurse = new Nurse();
        nurse.setName(dto.getName());
        nurse.setEmail(dto.getEmail());
        nurse.setExternalId(dto.getId());
        return nurse;
    }

    public static VitalsDto toVitalsDto(VitalRecord record) {
        VitalsDto dto = new VitalsDto();
        dto.setHeight("" + record.getHeight());
        dto.setWeight("" + record.getWeight());
        dto.setTemperature("" + record.getTemperature());
        dto.setPulse(record.getPulse());
        dto.setBloodpressureLow(record.getBloodPressureLow());
        dto.setBloodpressureHigh(record.getBloodPressureHigh());
        dto.setPatientId(record.getPatient().getExternalId());
        return dto;
    }

    public static Patient toPatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setExternalId(dto.getId());
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setDob(dto.getDob());
        return patient;
    }

    public static VisitingSchedule toSchedule(ScheduleDto dto) {
        VisitingSchedule schedule = new VisitingSchedule();
        schedule.setExternalId(dto.getId());
        schedule.setAppointmentDate(dto.getAppointmentTime());
        return schedule;
    }

    public static VitalRecord toVitalRecord(VitalsDto dto) {
        VitalRecord record = new VitalRecord();
        record.setExternalId(dto.getId());
        record.setHeight(Double.valueOf(dto.getHeight()));
        record.setWeight(Double.valueOf(dto.getWeight()));
        record.setPulse(dto.getPulse());
        record.setTemperature(Double.valueOf(dto.getTemperature()));
        record.setBloodPressureLow(dto.getBloodpressureLow());
        record.setBloodPressureHigh(dto.getBloodpressureHigh());
        record.setDateMeasured(dto.getDateCreated());
        record.setModified(false);
        return record;
    }
}
