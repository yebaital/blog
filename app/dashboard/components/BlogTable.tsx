import {Button} from "@/components/ui/button";
import {EyeOpenIcon, Pencil2Icon, TrashIcon} from "@radix-ui/react-icons";
import {Switch} from "@/components/ui/switch";

export default function BlogTable() {
    return <div className="overflow-x-auto">
        <div className="border bg-gradient-dark rounded-md w-[800px] md:w-full px-2">
            <div className="grid grid-cols-7 p-5 text-gray-500 border-b">
                <h1 className="col-span-2">Title</h1>
                <h1>Premium</h1>
                <h1>Published</h1>
            </div>
            <div className="grid grid-cols-7 p-5">
                <h1 className="col-span-2">Blog title</h1>
                <Switch checked={false}/>
                <Switch checked={false}/>
                <Actions/>
            </div>
        </div>
    </div>
}

const Actions = () => {
    return (
        <div className="flex items-center gap-2 flex-wrap col-span-2">
            <Button variant="outline" className="flex items-center gap-2"><EyeOpenIcon/>View</Button>
            <Button variant="outline" className="flex items-center gap-2"><TrashIcon/>Delete</Button>
            <Button variant="outline" className="flex items-center gap-2"><Pencil2Icon/>Edit</Button>
        </div>
    );
}