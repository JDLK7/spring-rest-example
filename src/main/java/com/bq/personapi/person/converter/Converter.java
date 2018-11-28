package com.bq.personapi.person.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<Dto, Model> {

//    Model inboundDtoToModel(Dto dto);
    Dto modelToDto(Model model);

    Model dtoToModel(Dto dto);

    default List<Dto> modelListToDtoList(final List<Model> models) {
        return models.stream().map(model -> modelToDto(model)).collect(Collectors.toList());
    }


}
