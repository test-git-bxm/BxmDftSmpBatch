package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;

import bxm.batch.dft.context.DefaultBatchApplicationContext;
import bxm.common.annotaion.BxmCategory;
import bxm.common.util.StringUtils;
import bxm.container.annotation.BxmBean;
import bxm.dft.app.DefaultApplicationException;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;

/**
 * @file bxm.jbk.smp.bean.MSmpTaskletBtch.java
 * @filetype java source file
 * @brief
 * @author 개발자(한글이름)
 * @version 0.1
 * @history
 * 
 *          <pre>
 * 버전     				성명                  				일자              				변경내용
 * -------   		----------------       	-----------      	-----------------
 * 0.1         		개발자(한글이름)       		2015. 01. 06.    	신규 작성
 *          </pre>
 */

@BxmBean("MSmpTaskletBtch")
@Scope("step")
@BxmCategory(logicalName = "Tasklet 샘플")
public class MSmpTaskletBtch implements Tasklet {

	final Logger logger = LoggerFactory.getLogger(MSmpTaskletBtch.class);

	private DSmpEmpTmp001 dSmpEmpTmp100;
	private DSmpEmpTst001 dSmpEmpTst100;

	/**
	 * Tasklet - 단순 DB CRUD 수행이나 반드시 한번에 Commit/Rollback 되어야 하는 배치에서 사용하는 패턴.
	 */
	@Override
	@BxmCategory(logicalName = "Tasklet 샘플(execute)")
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		if (dSmpEmpTmp100 == null) {
			dSmpEmpTmp100 = DefaultApplicationContext.getBean(DSmpEmpTmp001.class);
		}
		if (dSmpEmpTst100 == null) {
			dSmpEmpTst100 = DefaultApplicationContext.getBean(DSmpEmpTst001.class);
		}

		/**
		 * Tsklet의 경우 Step 간에 데이터 공유하기 위한 배치 JOB의 ExecutionContext를 가져오는 방법.
		 */
		ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution()
				.getExecutionContext();
		executionContext.putString("taskletName", "MSmpTaskletBtch"); // 공유할 값 설정

		/**
		 * 배치입력아규먼트 Get
		 */
		String oDate = DefaultBatchApplicationContext.getJobParameter("oDate");
		if (StringUtils.isEmpty(oDate)) {
			throw new DefaultApplicationException("CMFWE0001", new Object[] { "배치실행일자(oDate)" });
		}

		/**
		 * Tasklet 업무로직 수행 - 해당 Sample은 SMP_EMP_TST 에서 SMP_EMP_TMP로 데이터를 insert 하는 DBIO를
		 * 호출한다.
		 */
		// 샘플용직원정보TMP 테이블 초기화
		dSmpEmpTmp100.delete01();

		// 샘플용직원정보TMP 테이블 데이터 생성
		int insertCount = dSmpEmpTmp100.insert01();
		logger.debug("Insert Count : {}", insertCount);

		/**
		 * Tasklet을 종료처리 - Tasklet을 완료처리/ 혹은 반복 처리등을 Return 값에 따라 처리 할 수 있다. -
		 * RepeatStatus.FINISHED : Tasklet 완료(정상종료) - RepeatStatus.CONTINUABLE :
		 * Tasklet의 excute메소드 다시 수행. - 만일 에러를 발생하고 싶다면 ApplicationException을 throw 하면
		 * 된다.
		 */
		return RepeatStatus.FINISHED;
	}
}
