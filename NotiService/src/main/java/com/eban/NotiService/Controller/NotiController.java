package com.eban.NotiService.Controller;

import com.eban.NotiService.Configs.MyTextWebSocketHandler;
import com.eban.NotiService.DTO.NotiCreateRequest;
import com.eban.NotiService.DTO.NotiListResponse;
import com.eban.NotiService.Model.Noti;
import com.eban.NotiService.Service.ServiceImpl.NotiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noti")
public class NotiController {

    @Autowired
    private NotiServiceImpl notiService;

    @GetMapping
    public ResponseEntity<Object> getNotiDetail(@RequestParam String notiId) {
        try {
            Noti noti = notiService.getNoti(notiId);
            return ResponseEntity.status(HttpStatus.OK).body(noti);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
    }

     @GetMapping("/list")
     public ResponseEntity<Object> getNotiList(@RequestParam String userId, int page, int size) {
        try {
            Page<NotiListResponse> notis = notiService.getListNotiByUserId(userId,page,size);
            return ResponseEntity.status(HttpStatus.OK).body(notis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
     }

    @PostMapping
    public ResponseEntity<Object> createNoti(@RequestBody NotiCreateRequest noti) {
        try {
            Noti result = notiService.createNoti(new Noti(noti.getType(), noti.getCreaterId(), noti.getUserId()));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteNoti(@RequestBody String notiId) {
        try {
            notiService.deleteNoti(notiId);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    //websocket
    @Autowired
    private MyTextWebSocketHandler handler;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        handler.broadcast(message);
        return ResponseEntity.ok("Message sent via WebSocket.");
    }
}
