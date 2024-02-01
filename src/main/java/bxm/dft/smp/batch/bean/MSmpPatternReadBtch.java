package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.dft.smp.batch.bean.dto.MSmpPatternReadBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpPatternReadBtch02Dto;
import bxm.omm.root.IOmmObject;

/**
 * @file         	bxm.jbk.smp.bean.MSmpPatternReadBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          		성명                   	일자              		변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)      	2017. 11. 03.       신규 작성
 * </pre>
 */

@BxmBean("MSmpPatternReadBtch")
@Scope("step")
@BxmCategory(logicalName = "PATTERN READ 샘플", description = "PATTERN READ 샘플")
public class MSmpPatternReadBtch implements ItemProcessor<IOmmObject, IOmmObject> {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public IOmmObject process(IOmmObject in) throws Exception {
		if (in instanceof MSmpPatternReadBtch01Dto) {
			logger.info("patternA : [{}]", in);
		} else if (in instanceof MSmpPatternReadBtch02Dto) {
			logger.info("patternB : [{}]", in);
		} else {
			logger.error("Invalid pattern type... [{}]", in);
		}

		return in;
	}
}