package com.ar.rmsbackend.feature.item;

import com.ar.rmsbackend.common.constant.ErrorId;
import com.ar.rmsbackend.common.exception.RmsServerException;
import com.ar.rmsbackend.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ItemServiceImpl extends AbstractService<Item, ItemDto> implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        super(itemRepository);
        this.itemRepository = itemRepository;
    }


    @Override
    protected ItemResponseDto convertToResponseDto(Item item) {

        return ItemResponseDto.builder()
                .id(item.getId())
                .code(item.getCode())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }

    @Override
    public void validateClientData(ItemDto dto, Long id) {
        Optional<Item> exItem = itemRepository.findByCodeAndIsActiveTrue(dto.getCode());
        if (Objects.isNull(id)) {
            if (exItem.isPresent()) {
                throw RmsServerException.badRequest(ErrorId.ITEM_ALREADY_EXIST);
            }
        } else {
            if (exItem.isPresent()) {
                if (!exItem.get().getId().equals(id)) {
                    throw RmsServerException.badRequest(ErrorId.ITEM_ALREADY_EXIST);
                }
            }
        }
    }

    @Override
    protected Item convertToEntity(ItemDto dto) {
        return mapToEntity(new Item(), dto);
    }

    @Override
    protected Item convertToEntity(ItemDto dto, Item entity) {
        return mapToEntity(entity, dto);
    }


    private Item mapToEntity(Item entity, ItemDto dto) {
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
