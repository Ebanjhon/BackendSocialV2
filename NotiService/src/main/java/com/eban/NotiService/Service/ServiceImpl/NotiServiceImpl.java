package com.eban.NotiService.Service.ServiceImpl;

import com.eban.NotiService.Configs.MyTextWebSocketHandler;
import com.eban.NotiService.DTO.NotiListResponse;
import com.eban.NotiService.DTO.User;
import com.eban.NotiService.Model.Noti;
import com.eban.NotiService.Repository.NotiRepository;
import com.eban.NotiService.Service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotiServiceImpl implements NotiService {

    @Autowired
    private MyTextWebSocketHandler webSocketHandler;

    @Autowired
    private NotiRepository notiRepository;

    @Autowired
    private UserGrpc getUserGrpc;

    @Override
    public Noti getNoti(String notiId) {
        return notiRepository.getNotiByNotiId(notiId).get();
    }

    @Override
    public Noti createNoti(Noti data) {
        if(!data.getCreaterId().equals(data.getUserId())){
            webSocketHandler.sendMessageToUser(data.getUserId(), data.getTypeNotification().toString());
        }
        return notiRepository.save(data);
    }

    @Override
    public Page<NotiListResponse> getListNotiByUserId(String userId, int page, int size) {
        Page<Noti> notiPage = notiRepository.findByUserId(userId, PageRequest.of(page, size));

        List<NotiListResponse> notiListResponses = notiPage.getContent().stream()
                .map(noti -> {
                    User user = getUserGrpc.getUserById(noti.getCreaterId());
                    return new NotiListResponse(noti, user);
                })
                .collect(Collectors.toList());
        return new PageImpl<>(notiListResponses, notiPage.getPageable(), notiPage.getTotalElements());
    }

    @Override
    public void deleteNoti(String notiId) {
        System.out.println(notiId);
        Noti n = notiRepository.getNotiByNotiId(notiId).get();
        System.out.println(n);
        notiRepository.delete(n);
    }

}
