var fields = ["ruleId", "ruleName", "ruleCode", "ruleType", "ruleContent",
			"startTime", "endTime", "ruleValue", "ruleNum", "ruleNote",
			"modifiedDate", "ruleStatus", "specialStartTime", "specialEndTime",
			"specialRuleValue", "specialRuleNum", "specialScoreMemo"];

var scoreRuleStore = new Ext.data.JsonStore({
				root : "result",
				url : path + "/imusic/score/scoreRule!list.action",
				totalProperty : "totalCount",
				fields : fields
});

var scoreRuleReader = new Ext.data.JsonReader({
	root:"result",
	totalProperty : "totalCount"
},[
	{name:"ruleId",mapping:"ruleId"},
	{name:"ruleName",mapping:"ruleName"},
	{name:"ruleCode",mapping:"ruleCode"},
	{name:"ruleType",mapping:"ruleType"},
	{name:"ruleContent",mapping:"ruleContent"},
	{name:"startTime",mapping:"startTime"},
	{name:"endTime",mapping:"endTime"},
	{name:"ruleValue",mapping:"ruleValue"},
	{name:"ruleNum",mapping:"ruleNum"},
	{name:"ruleNote",mapping:"ruleNote"},
	{name:"modifiedDate",mapping:"modifiedDate"},
	{name:"ruleStatus",mapping:"ruleStatus"},
	{name:"specialStartTime",mapping:"specialStartTime"},
	{name:"specialEndTime",mapping:"specialEndTime"},
	{name:"specialRuleValue",mapping:"specialRuleValue"},
	{name:"specialRuleNum",mapping:"specialRuleNum"},
	{name:"specialScoreMemo",mapping:"specialScoreMemo"}
]);