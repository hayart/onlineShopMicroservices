package am.shopfx.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import am.shopfx.core.entity.ErrorLog;

@Repository
public interface ErrorLogDao extends JpaRepository<ErrorLog, Long> {

}
