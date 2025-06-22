package com.hu6r1s.bloom.chat.dto.response;

public record ChatRoomListDto(
    String roomId,
    String myUserId,
    String partnerNickname
) {

}
