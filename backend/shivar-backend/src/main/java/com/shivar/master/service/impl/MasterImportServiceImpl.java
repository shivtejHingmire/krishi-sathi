package com.shivar.master.service.impl;

import com.shivar.master.entity.District;
import com.shivar.master.entity.State;
import com.shivar.master.entity.Taluka;
import com.shivar.master.entity.Village;
import com.shivar.master.repository.DistrictRepository;
import com.shivar.master.repository.StateRepository;
import com.shivar.master.repository.TalukaRepository;
import com.shivar.master.repository.VillageRepository;
import com.shivar.master.service.MasterImportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterImportServiceImpl implements MasterImportService {

    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final TalukaRepository talukaRepository;
    private final VillageRepository villageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void importMasterData() {

        Map<Long, State> stateCache = stateRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        State::getLgdCode,
                        Function.identity()
                ));

        Map<Long, District> districtCache = districtRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        District::getLgdCode,
                        Function.identity()
                ));

        Map<Long, Taluka> talukaCache = talukaRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Taluka::getLgdCode,
                        Function.identity()
                ));

        Map<Long, Village> villageCache = villageRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Village::getLgdCode,
                        Function.identity()
                ));

        List<Village> villageBatch = new ArrayList<>();

        int count = 0;

        ClassPathResource resource =
                new ClassPathResource("master/maharashtra_master.csv");

        try (
                Reader reader = new InputStreamReader(resource.getInputStream());

                CSVParser csvParser = new CSVParser(
                        reader,
                        CSVFormat.DEFAULT.builder()
                                .setHeader()
                                .setSkipHeaderRecord(true)
                                .build())
        ) {

            for (CSVRecord record : csvParser) {

                State state = getOrCreateState(record, stateCache);

                District district = getOrCreateDistrict(record, state, districtCache);

                Taluka taluka = getOrCreateTaluka(record, district, talukaCache);

                Village village = createVillage(record, taluka, villageCache);

                if (village != null) {
                    villageBatch.add(village);
                }

                count++;

                if (villageBatch.size() == 1000) {

                    villageRepository.saveAll(villageBatch);

                    villageBatch.clear();

                    entityManager.flush();
                    entityManager.clear();

                    System.out.println("Imported : " + count);
                }
            }

            if (!villageBatch.isEmpty()) {
                villageRepository.saveAll(villageBatch);
            }

            System.out.println("Master Data Imported Successfully.");

        } catch (IOException e) {

            throw new RuntimeException("CSV Import Failed", e);

        }
    }

    private State getOrCreateState(CSVRecord record,
                                   Map<Long, State> stateCache) {

        Long stateCode = Long.parseLong(record.get("stateCode"));

        State state = stateCache.get(stateCode);

        if (state != null) {
            return state;
        }

        state = new State();
        state.setLgdCode(stateCode);
        state.setNameEnglish(record.get("stateNameEnglish"));
        state.setNameLocal(record.get("stateNameLocal"));

        state = stateRepository.save(state);

        stateCache.put(stateCode, state);

        return state;
    }

    private District getOrCreateDistrict(CSVRecord record,
                                         State state,
                                         Map<Long, District> districtCache) {

        Long districtCode = Long.parseLong(record.get("districtCode"));

        District district = districtCache.get(districtCode);

        if (district != null) {
            return district;
        }

        district = new District();
        district.setLgdCode(districtCode);
        district.setNameEnglish(record.get("districtNameEnglish"));
        district.setNameLocal(record.get("districtNameLocal"));
        district.setState(state);

        district = districtRepository.save(district);

        districtCache.put(districtCode, district);

        return district;
    }

    private Taluka getOrCreateTaluka(CSVRecord record,
                                     District district,
                                     Map<Long, Taluka> talukaCache) {

        Long talukaCode =
                Long.parseLong(record.get("subdistrictCode"));

        Taluka taluka = talukaCache.get(talukaCode);

        if (taluka != null) {
            return taluka;
        }

        taluka = new Taluka();
        taluka.setLgdCode(talukaCode);
        taluka.setNameEnglish(record.get("subdistrictNameEnglish"));
        taluka.setNameLocal(record.get("subdistrictNameLocal"));
        taluka.setDistrict(district);

        taluka = talukaRepository.save(taluka);

        talukaCache.put(talukaCode, taluka);

        return taluka;
    }

    private Village createVillage(CSVRecord record,
                                  Taluka taluka,
                                  Map<Long, Village> villageCache) {

        Long villageCode = Long.parseLong(record.get("villageCode"));

        // Already exists in cache
        if (villageCache.containsKey(villageCode)) {
            return null;
        }

        Village village = new Village();

        village.setLgdCode(villageCode);
        village.setNameEnglish(record.get("villageNameEnglish"));
        village.setNameLocal(record.get("villageNameLocal"));

        String censusCode = record.get("villageCensus2011Code");

        if (censusCode != null && !censusCode.isBlank()) {
            village.setCensusCode(Long.parseLong(censusCode));
        }

        village.setTaluka(taluka);

        // Add to cache immediately
        villageCache.put(villageCode, village);

        return village;
    }
}