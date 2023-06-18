package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Notice;
import it.uniroma3.siw.repository.NoticeRepository;

@Service
public class NoticeService {
	
	@Autowired
    protected NoticeRepository noticeRepository;

	@Transactional
    public Notice createNewNotice(Notice notice) {
        return this.noticeRepository.save(notice);
    }

	public List<Notice> findAllNotices() {
		return (List<Notice>) this.noticeRepository.findAll();
	}
}
