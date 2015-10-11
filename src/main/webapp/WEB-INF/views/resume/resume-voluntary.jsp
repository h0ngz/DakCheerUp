<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./config/resume_head.jsp" flush="false" />

<div data-scrollbar="true" data-height="100%" class="m-l-15 m-r-15">
  <div class="row">
    <div class="col-md-12">
      <!-- begin panel -->
      <div id="resume-voluntary" class="panel panel-inverse" data-sortable-id="ui-widget-11">
        <div class="panel-heading">
          <div class="panel-heading-btn">
            <a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
            <a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
            <a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
            <a href="javascript:" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
          </div>
          <h4 class="panel-title">봉사활동 / 대외활동</h4>
        </div>
        <div class="panel-body">
          <form id="resume-voluntary-form" name="voluntary" class="form-horizontal" action="/resume/voluntary" method="POST">
            <div class="table-responsive">
              <table id="voluntaryTbl" class="table table-bordered table-condensed table-valign-middle text-center">
                <tbody>
                <tr class="warning">
                  <td class="width-50">
                    <a href="javascript:add_voluntary();"><i class="ion-plus-round fa fa-lg text-success"></i></a>
                  </td>
                  <td class="width-150">기간</td>
                  <td>봉사기관</td>
                  <td>활동내용</td>
                </tr>
                <c:choose>
                	<c:when test="${empty voluntaryList}">
		                <tr>
		                  <td>
		                    <a href="javascript:" class="btn btn-danger btn-icon btn-xs btn-circle"><i class="ion-minus-round fa fa-lg text-white"></i></a>
		                  </td>
		                  <td>
		                    <input name="newVoluntaryPeriod" type="text" class="form-control input-sm masked-input-period"/>
		                  </td>
		                  <td>
		                    <input name="newVoluntaryOrg"  maxlength="25" type="text" class="form-control input-sm"/>
		                  </td>
		                  <td>
		                    <input name="newVoluntaryContent" maxlength="50" type="text" class="form-control input-sm"/>
		                  </td>
		                </tr>
                	</c:when>
                	<c:when test="${not empty voluntaryList}">
	                	<c:forEach items="${voluntaryList}" var="voluntary">
			                <tr>
			                  <td>
			                    <a href="/resume/voluntary/delVoluntary?voluntaryNo=${voluntary.voluntaryNo}" class="btn btn-danger btn-icon btn-xs btn-circle"><i class="ion-minus-round fa fa-lg text-white"></i></a>
			                  </td>
			                  <td>
			                  	<input name="voluntaryNo" value="${voluntary.voluntaryNo}" type="hidden" />
			                    <input name="voluntaryPeriod" value="${voluntary.voluntaryPeriod}" type="text" class="form-control input-sm masked-input-period"/>
			                  </td>
			                  <td>
			                    <input name="voluntaryOrg" value="${voluntary.voluntaryOrg}" maxlength="25" type="text" class="form-control input-sm"/>
			                  </td>
			                  <td>
			                    <input name="voluntaryContent" value="${voluntary.voluntaryContent}" maxlength="50" type="text" class="form-control input-sm"/>
			                  </td>
			                </tr>
			        	</c:forEach>
                	</c:when>
                </c:choose>
                </tbody>
              </table>
            </div>

            <div class="m-t-0 m-b-10 text-right">
              <a href="#" onClick="document.getElementById('resume-voluntary-form').submit()" class="btn btn-warning btn-sm">&nbsp; 저장 &nbsp;</a>
            </div>
          </form>
        </div>
      </div>
      <!-- end panel -->
    </div>
  </div>
</div>


<jsp:include page="./config/resume_footer.jsp" flush="false" />