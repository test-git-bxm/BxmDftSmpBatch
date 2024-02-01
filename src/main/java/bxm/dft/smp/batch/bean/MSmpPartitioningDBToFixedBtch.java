package bxm.dft.smp.batch.bean;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.context.das.DasUtils;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpDBToFixedBtch01Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01InDto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto;

/**
 * <b>BXM Business class</b>
 * <p>
 * <b>Revision history</b><br>
 * <pre>
 * 2018.09.28 : New creation
 * </pre>
 *
 * @since 2018.09.28
 * @version 1.0.0
 * @author dohoon
 */
@BxmBean("MSmpPartitioningDBToFixedBtch")
@Scope("step")
@BxmCategory(logicalName = "MSmpPartitioningDBToFixedBtch")
public class MSmpPartitioningDBToFixedBtch implements ItemStream, ItemReader<MSmpDBToFixedBtch01Dto>, ItemProcessor<MSmpDBToFixedBtch01Dto, MSmpDBToFixedBtch01Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private DSmpEmpTst001 dSmpEmpTst001; // 샘플용직원정보TST
	
	private Iterator<DSmpEmpTst001selectList01OutDto> iterator;
	
	/**
	 * open
	 * - 초기화를 위해 구현해야 하는 Method 로서 스텝이 시작되기 전에 FW에서 최초 1번 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "open : 샘플용 직원정보 Iterator 처리", description = "open : 샘플용 직원정보 Iterator 처리")
	public void open(ExecutionContext executionContext) throws ItemStreamException 
	{
		dSmpEmpTst001 = DefaultApplicationContext.getBean(dSmpEmpTst001, DSmpEmpTst001.class);
		
		/**
		 * 배치 입력파라미터 "deptNo" Get
		 */
		int deptNo = executionContext.getInt("deptNo");
		
		/**
		 * 입력받은 "deptNo" 에 대하여 샘플용 직원정보를 Iterator로 가져온다.
		 */
		DSmpEmpTst001selectList01InDto inDto = new DSmpEmpTst001selectList01InDto();
		inDto.setFeduDeptNo(deptNo);
		iterator = dSmpEmpTst001.selectList01(inDto).iterator();
		if(iterator == null)
		{
			throw new ItemStreamException("connected result is not prepared.");
		}
	}
	
	/**
	 * read
	 * - 처리 대상 데이터를 건 별로 DB 또는 파일에서 읽어오는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "read : 샘플용 직원정보 Read", description = "read : 샘플용 직원정보를 1건씩 Read한다.")
	public MSmpDBToFixedBtch01Dto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException 
	{
		MSmpDBToFixedBtch01Dto out = null;
		if(iterator.hasNext())
		{
			out = new MSmpDBToFixedBtch01Dto();
			DSmpEmpTst001selectList01OutDto input = iterator.next();
			
			out.setFeduEmpNo(input.getFeduEmpNo());			// set [FW샘플 임직원번호]
			out.setFeduEmpNm(input.getFeduEmpNm());			// set [FW샘플 임직원명]
			out.setFeduOccpNm(input.getFeduOccpNm());		// set [FW샘플 직업명]
			out.setFeduMngrEmpNo(input.getFeduMngrEmpNo());	// set [FW샘플 관리자임직원번호]
			out.setFeduHireDt(input.getFeduHireDt());		// set [FW샘플 입사일자]
			out.setFeduPayAmt(input.getFeduPayAmt());		// set [FW샘플 급여금액]
			out.setFeduDeptNo(input.getFeduDeptNo());		// set [FW샘플 부서번호]
		}
		
		return out;
	}
	
	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리", description = "process : 샘플용 직원정보를 1건씩 처리한다.")
	public MSmpDBToFixedBtch01Dto process(MSmpDBToFixedBtch01Dto in) throws Exception 
	{
		MSmpDBToFixedBtch01Dto out;
		
		/**
		 *  관리자임직원번호가 없는 경우에는 null 를 return 하여 해당 Item에 대하여 Skip 처리한다.
		 *  - 참고 : process에서 null을 return 하면 write로 item이 넘어가지 않는다.
		 */
		if(in.getFeduMngrEmpNo() == 0)
		{
			logger.warn("임직원번호[{}]에 대한 관리자 번호가 0 입니다. Skip 처리합니다.", in.getFeduEmpNo());
			return null;
		}
		
		
		/**
		 * 개별 업무로직 수행
		 * ...
		 * ...
		 */
		out = in;		
		
		return out;
	}
	
	/**
	 * update
	 * - 진행 상태를 기록하기 위해 구현해야 하는 Method로서 구간 별로 Commit 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "update", description = "update")
	public void update(ExecutionContext executionContext) throws ItemStreamException 
	{
		
	}
	
	/**
	 * close
	 * - 리소스 정리 작업을 위해 구현해야 하는 Method로서 Step 완료 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "close", description = "close")
	public void close() throws ItemStreamException 
	{
		if(iterator != null) DasUtils.disconnectDasExecutor(iterator);
	}
}