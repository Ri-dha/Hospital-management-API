package com.azu.hospital.bulding.Shift.shift_timings.dao.dao_timing_arrange;

import com.azu.hospital.bulding.Shift.shift_timings.entity.TimingsArrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("TimingsArrangeJpa")
public class TimingsArrangeJpaDataAccess implements TimingsArrangeDao{

    private final TimingsArrangeRepository repository;

    @Autowired
    public TimingsArrangeJpaDataAccess(TimingsArrangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addListOfTimingsArrange(List<TimingsArrange> requests) {
        repository.saveAll(new ArrayList<>(requests));
    }

    @Override
    public void updateTimingsArrange(List<TimingsArrange> update) {
        repository.saveAll(new ArrayList<>(update));
    }

    @Override
    public Optional<TimingsArrange> getTimingArrangeById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteTimingsArrangeById(Long id) {
        repository.deleteById(id);
    }
}
