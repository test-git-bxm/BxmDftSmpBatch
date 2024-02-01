package bxm.dft.smp.batch.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;

import bxm.batch.dft.context.DefaultBatchApplicationContext;
import bxm.batch.dft.util.DefaultFileUtils;
import bxm.batch.item.file.ext.DelimitedFileReader;
import bxm.batch.item.file.ext.FixedFileReader;
import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileReadBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileReadBtch02Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileReadBtch03Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;

/**
 * @file         	bxm.jbk.smp.bean.MSmpMultiFileReadBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          		성명                   	일자              		변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)      	2015. 01. 06.       신규 작성
 * </pre>
 */

@BxmBean("MSmpMultiFileReadBtch")
@Scope("step")
@BxmCategory(logicalName = "MULTI FILE READ 샘플", description = "MULTI FILE READ 샘플")
public class MSmpMultiFileReadBtch implements ItemStream, ItemReader<MSmpMultiFileReadBtch01Dto>,
		ItemProcessor<MSmpMultiFileReadBtch01Dto, MSmpMultiFileReadBtch03Dto>, ItemWriter<MSmpMultiFileReadBtch03Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DSmpEmpTmp001 dSmpEmpTmp100;

	private FixedFileReader<MSmpMultiFileReadBtch01Dto> empInfoFixedReader = null;
	private DelimitedFileReader<MSmpMultiFileReadBtch02Dto> empInfoDelimitedReader = null;

	private int currentEmpNo;

	/**
	 * open
	 * - 초기화를 위해 구현해야 하는 Method 로서 스텝이 시작되기 전에 FW에서 최초 1번 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "open : 샘플용 직원정보 File Open 처리", description = "open : 샘플용 직원정보 File Open 처리")
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		/**
		 * 배치입력아규먼트에서 입력된 Order Date 를 가지고 온다.
		 */
		String oDate = DefaultBatchApplicationContext.getJobParameter("oDate");

		/**
		 * 샘플용 직원1정보 Fixed File Open
		 */
		String empInfoFixedFileName = "/data1/prod/bxm400/dat/fixed_file_" + oDate + ".txt";
		empInfoFixedReader = DefaultFileUtils.getFixedFileReader(empInfoFixedFileName, MSmpMultiFileReadBtch01Dto.class,
				"UTF-8");
		empInfoFixedReader.open(executionContext); // File Open

		/**
		 * 샘플용 직원2정보 Delimited File Open 
		 */
		String empInfoDelimitedFileName = "/data1/prod/bxm400/dat/delimited_file_" + oDate + ".txt";
		empInfoDelimitedReader = DefaultFileUtils.getDelimitedFileReader(empInfoDelimitedFileName,
				MSmpMultiFileReadBtch02Dto.class, ";", "UTF-8");
		empInfoDelimitedReader.open(executionContext); // File Open

	}

	/**
	 * read
	 * - 처리 대상 데이터를 건 별로 DB 또는 파일에서 읽어오는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "read : 샘플용 직원정보 Read", description = "read : 샘플용 직원1정보를 1건씩 Read한다.")
	public MSmpMultiFileReadBtch01Dto read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		/**
		 * 샘플용 직원정보를 1건씩 read하여 return 한다.
		 * - empInfoReadr 에서 null 을 return 하면 배치는 종료된다.
		 */
		MSmpMultiFileReadBtch01Dto out = empInfoFixedReader.read();

		return out;
	}

	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리", description = "process : 샘플용 직원1정보를 샘플용 직원2정보를 Read하여 비교 처리한다.")
	public MSmpMultiFileReadBtch03Dto process(MSmpMultiFileReadBtch01Dto in) throws Exception {
		MSmpMultiFileReadBtch03Dto out = null;

		if (dSmpEmpTmp100 == null) {
			dSmpEmpTmp100 = DefaultApplicationContext.getBean(DSmpEmpTmp001.class);
		}

		/**
		 * 샘플용 직원1정보파일에 대하여 샘플용 직원2정보파일와 비교하여 샘플용 직원2정보파일에 직원정보가 있는지 체크한다.
		 * - 직원2정보파일에 직원정보가 존재하면 writer로 데이터 전달
		 * - 직원2정보파일에 직원정보가 없으면 null return.
		 */
		if (in.getFeduEmpNo() != currentEmpNo) {
			MSmpMultiFileReadBtch02Dto mSmpMultiFileReadBtch02Dto = null;
			while ((mSmpMultiFileReadBtch02Dto = empInfoDelimitedReader.read()) != null) {
				if (in.getFeduEmpNo() <= mSmpMultiFileReadBtch02Dto.getFeduEmpNo()) {
					currentEmpNo = mSmpMultiFileReadBtch02Dto.getFeduEmpNo();
					break;
				}
			}
		}
		if (in.getFeduEmpNo() != currentEmpNo) {
			return null;
		}

		/**
		 *  write에 전달할 Item 설정
		 */
		out = new MSmpMultiFileReadBtch03Dto();
		DSmpEmpTmp001Dto dSmpEmpTmp100insert02InDto = new DSmpEmpTmp001Dto();

		dSmpEmpTmp100insert02InDto.setFeduEmpNo(in.getFeduEmpNo()); // set [FW샘플 임직원번호]
		dSmpEmpTmp100insert02InDto.setFeduEmpNm(in.getFeduEmpNm()); // set [FW샘플 임직원명]
		dSmpEmpTmp100insert02InDto.setFeduOccpNm(in.getFeduOccpNm()); // set [FW샘플 직업명]
		dSmpEmpTmp100insert02InDto.setFeduMngrEmpNo(in.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
		dSmpEmpTmp100insert02InDto.setFeduHireDt(in.getFeduHireDt()); // set [FW샘플 입사일자]
		dSmpEmpTmp100insert02InDto.setFeduPayAmt(in.getFeduPayAmt()); // set [FW샘플 급여금액]
		dSmpEmpTmp100insert02InDto.setFeduDeptNo(in.getFeduDeptNo()); // set [FW샘플 부서번호]

		out.setGrid01(dSmpEmpTmp100insert02InDto);

		return out;
	}

	/**
	 * write
	 * - 처리 대상 결과 데이터를 커밋 단위 별로 DB 또는 파일에 기록처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "write : 샘플용 직원정보 처리", description = "write : 샘플용 직원정보를 등록한다.")
	public void write(List<? extends MSmpMultiFileReadBtch03Dto> in) throws Exception {
		/**
		 * List로 전달된 Item를 Loop를 돌면서 DB에 Insert할 DTO를 List형태로 설정한다.
		 */
		List<DSmpEmpTmp001Dto> dSmpEmpTmp100insert02InDtos = new ArrayList<DSmpEmpTmp001Dto>();
		for (MSmpMultiFileReadBtch03Dto mSmpMultiFileReadBtch03Dto : in) {
			dSmpEmpTmp100insert02InDtos.add(mSmpMultiFileReadBtch03Dto.getGrid01());
		}

		/**
		 * insert 처리
		 * - 참고 1 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 bulk로 쿼리가 수행이 된다.
		 * - 참고 2 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 수행이 되기때문에 return 값은 임의의 음수 값이다.(의미없음)
		 */
		dSmpEmpTmp100.insert02(dSmpEmpTmp100insert02InDtos);
	}

	/**
	 * update
	 * - 진행 상태를 기록하기 위해 구현해야 하는 Method로서 구간 별로 Commit 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "update", description = "update")
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (empInfoFixedReader != null)
			empInfoFixedReader.update(executionContext);
		if (empInfoDelimitedReader != null)
			empInfoDelimitedReader.update(executionContext);
	}

	/**
	 * close
	 * - 리소스 정리 작업을 위해 구현해야 하는 Method로서 Step 완료 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "close", description = "close")
	public void close() throws ItemStreamException {
		if (empInfoFixedReader != null)
			empInfoFixedReader.close();
		if (empInfoDelimitedReader != null)
			empInfoDelimitedReader.close();
	}
}