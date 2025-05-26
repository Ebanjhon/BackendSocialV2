package com.eban.NotiService.Service;

import com.eban.NotiService.DTO.NotiListResponse;
import com.eban.NotiService.Model.Noti;
import org.springframework.data.domain.Page;

public interface NotiService {

    Noti getNoti(String notiId);

    Noti createNoti(Noti data);

    Page<NotiListResponse> getListNotiByUserId(String userId, int page, int size);

    void deleteNoti(String notiId);
}
