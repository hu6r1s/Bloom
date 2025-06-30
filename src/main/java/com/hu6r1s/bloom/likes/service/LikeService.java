package com.hu6r1s.bloom.likes.service;

import com.hu6r1s.bloom.chat.entity.ChatRoom;
import com.hu6r1s.bloom.chat.repository.ChatRoomRepository;
import com.hu6r1s.bloom.global.exception.LikeDuplicationException;
import com.hu6r1s.bloom.global.exception.SelfLikeException;
import com.hu6r1s.bloom.likes.dto.response.LikeResponseDto;
import com.hu6r1s.bloom.likes.entity.Like;
import com.hu6r1s.bloom.likes.entity.LikeStatus;
import com.hu6r1s.bloom.likes.repository.LikeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

  private final LikeRepository likeRepository;
  private final ChatRoomRepository chatRoomRepository;

  public LikeResponseDto pressLike(String likingUserId, String likedUserId) {
    if (likingUserId.equals(likedUserId)) {
      throw new SelfLikeException("자기 자신을 '좋아요' 할 수 없습니다.");
    }

    if (likeRepository.existsByLikingUserIdAndLikedUserId(likingUserId, likedUserId)) {
      throw new LikeDuplicationException("이미 '좋아요'를 누른 상대입니다.");
    }

    Like newLike = new Like(likingUserId, likedUserId);
    likeRepository.save(newLike);
    boolean isMatched = likeRepository.existsByLikingUserIdAndLikedUserId(likedUserId, likingUserId);
    if (isMatched) {
      ChatRoom newChatRoom = new ChatRoom(Set.of(likingUserId, likedUserId));
      ChatRoom savedChatRoom = chatRoomRepository.save(newChatRoom);

      return new LikeResponseDto(LikeStatus.MATCHED, savedChatRoom.getId());
    }
    return new LikeResponseDto(LikeStatus.LIKED, null);
  }
}
