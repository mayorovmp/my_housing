package org.pika.my_housing.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.pika.my_housing.dto.BillDto;
import org.pika.my_housing.dto.CounterDto;
import org.pika.my_housing.dto.reading.ReadingDto;
import org.pika.my_housing.dto.ServiceDto;
import org.pika.my_housing.dto.reading.ReadingOnAddDto;
import org.pika.my_housing.dto.user.UserDto;
import org.pika.my_housing.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    @Scope("singleton")
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        configureService(mapper);
        configureBill(mapper);
        configureCounter(mapper);
        configureReading(mapper);
        configureReadingOnAddDto(mapper);

        return mapper;
    }

    private static void configureBill(ModelMapper modelMapper) {
        Converter<BillEntity, BillDto> myConverter = context -> {
            BillEntity s = context.getSource();
            BillDto d = context.getDestination();
            d.setTime(s.getPeriod());
            d.setValue(s.getValue().doubleValue());
            d.setService(modelMapper.map(s.getService(), ServiceDto.class));
            return d;
        };
        modelMapper.createTypeMap(BillEntity.class, BillDto.class).setPostConverter(myConverter);
    }

    private static void configureReading(ModelMapper mapper) {
        mapper.typeMap(ReadingEntity.class, ReadingDto.class);
    }

    private static void configureReadingOnAddDto(ModelMapper mapper) {
        mapper.createTypeMap(ReadingOnAddDto.class, ReadingEntity.class).setPostConverter(
                context -> {
                    var s = context.getSource();
                    var d = context.getDestination();
                    var counter = new CounterEntity();
                    counter.setId(s.getCounterId());
                    d.setCounter(counter);
                    return d;
                }
        );
    }

    private static void configureCounter(ModelMapper mapper) {

        mapper.createTypeMap(CounterEntity.class, CounterDto.class).setPostConverter(
                context -> {
                    var s = context.getSource();
                    var d = context.getDestination();
                    d.setReadings(s.getReadings().stream()
                            .map((x)->mapper.map(x, ReadingDto.class))
                            .collect(Collectors.toList()));
                    return d;
                }
        );
    }

    private static void configureService(ModelMapper mapper) {
        mapper.createTypeMap(ServiceEntity.class, ServiceDto.class).setPostConverter(
                context -> {
                    var s = context.getSource();
                    var d = context.getDestination();
                    return d;
                }
        );

    }
}
