package bxm.dft.smp.batch.bean;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;

/**
 * @file         bxm.dft.smp.batch.bean.MSmpPartitioning.java
 * @filetype     java source file
 * @brief
 * @author       개발자(한글이름)
 * @version      0.1
 * @history
 * <pre>
 * 버전          성명                   일자              변경내용
 * -------       ----------------       -----------       -----------------
 * 0.1           개발자(한글이름)       2016. 11. 29.       신규 작성
 * </pre>
 */
@BxmBean("MSmpPartitioning")
@Scope("step")
@BxmCategory(logicalName = "MSmpPartitioning", description = "MSmpPartitioning")
public class MSmpPartitioning implements Partitioner{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	@BxmCategory(logicalName = "MSmpPartitioning", description = "MSmpPartitioning")
	public Map<String, ExecutionContext> partition(int gridSize) 
	{
		/**
		 * Partition으로 분배한 Step의 ExecutionContext 정보를 전달하기 위한 Map
		 * - Map의 Size 만큼 병렬로 Step이 수행이 된다. (보통 전달받은 gridSize 값이 Map의 size로 정의된다.) 
		 */
		Map<String, ExecutionContext> executionContextMap = new HashMap<String, ExecutionContext>(); 
		
		/**
		 * gridSize 만큼 ExecutionContext Map에 설정한다.
		 */
		for(int ix = 1; ix <= gridSize; ix++)
		{
			/**
			 * ExecutionContext 값 설정
			 * - Partitioning으로 수행할 값을 설정처리하면 된다.
			 */
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.put("deptNo", ix * 10);
			
			/**
			 * Partition Id를 key로 ExecutionContext값을 설정한다. 
			 */
			executionContextMap.put("id_" + ix, executionContext);
		}
		
		return executionContextMap;
	}
	
}

