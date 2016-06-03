package com.jims.nurse.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.nurse.dao.BedRecDao;
import com.jims.nurse.entity.BedRec;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BedRecBo
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Service
@Component
@Transactional(readOnly = false)
public class BedRecBo extends CrudImplService<BedRecDao, BedRec> {


}
