/**
 * DBIO 에디터에서 생성된 파일입니다. 인터페이스 파일을 직접 수정하지 마십시오.
 * @Generated Tue Sep 06 14:28:25 KST 2016
 */
package bxm.dft.smp.batch.dbio;

import bxm.container.annotation.BxmDataAccess;
import bxm.common.annotaion.BxmCategory;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "all" })
@BxmDataAccess(mapper = "bxm/dft/smp/batch/dbio/DSmpDeptTst001.dbio", datasource = "MainDS")
@BxmCategory(logicalName = "샘플용부서정보001", description = "샘플용부서정보001")
public interface DSmpDeptTst001
{

	/**
	 * @TestValues 	feduDeptNo=10;
	 */
	@BxmCategory(description = "")
	bxm.dft.smp.batch.dbio.dto.DSmpDeptTst001Dto selectOne01(@Param("feduDeptNo") java.lang.Integer feduDeptNo);
}