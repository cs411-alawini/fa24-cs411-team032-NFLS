package com.runtrack.service;

import com.runtrack.entity.Host;
import com.runtrack.repository.HostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HostService {

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    // 添加 Host
    @Transactional
    public void addHost(String userId, String eventId) {
        if (!hostRepository.existsById(userId, eventId)) {
            hostRepository.save(new Host(userId, eventId));
        } else {
            throw new IllegalArgumentException("Host already exists");
        }
    }

    // 删除 Host
    @Transactional
    public void removeHost(String userId, String eventId) {
        if (hostRepository.existsById(userId, eventId)) {
            hostRepository.delete(userId, eventId);
        } else {
            throw new IllegalArgumentException("Host not found");
        }
    }

    // 根据 UserId 获取 Host
    public List<Host> getHostsByUserId(String userId) {
        return hostRepository.findByUserId(userId);
    }

    // 根据 EventId 获取 Host
    public List<Host> getHostsByEventId(String eventId) {
        return hostRepository.findByEventId(eventId);
    }
}
